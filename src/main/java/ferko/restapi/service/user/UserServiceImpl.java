package ferko.restapi.service.user;

import ferko.restapi.dao.country.CountryRepository;
import ferko.restapi.dao.doc.DocRepository;
import ferko.restapi.dao.document.DocumentDao;
import ferko.restapi.dao.office.OfficeDao;
import ferko.restapi.dao.user.UserDao;
import ferko.restapi.dto.user.*;
import ferko.restapi.mapper.MapperFacade;
import ferko.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;
    private final DocumentDao documentDao;
    private final DocRepository docRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, MapperFacade mapperFacade, DocumentDao documentDao, DocRepository docRepository, CountryRepository countryRepository) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.documentDao = documentDao;
        this.docRepository = docRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public UserGetDto findById(int id) {
        User user = userDao.findById(id);
        UserGetDto dto = new UserGetDto();
        mapperFacade.map(user, dto);
        dto.setDocName(documentDao.findById(id).getDoc().getDocName());
        dto.setCitizenshipName(user.getCountry().getCountryName());
        dto.setCitizenshipCode(user.getCountry().getCountryCode());
        return dto;
    }

    @Transactional
    @Override
    public void save(UserSaveDto userSaveDTO) {
        User user = new User();
        mapperFacade.map(userSaveDTO, user);
        user.setOffice(officeDao.findById(userSaveDTO.getOfficeId()));
        user.setCountry(countryRepository.findByCountryCode(userSaveDTO.getCitizenshipCode()).get());
        user.getDocument().setUser(user);
        userDao.save(user);
        user.getDocument().setDoc(docRepository.findByDocCode(userSaveDTO.getDocCode()).get());

    }

    @Transactional
    @Override
    public void update(UserUpdateDto userUpdateDTO) {
        User user = userDao.findById(userUpdateDTO.getId());
        mapperFacade.map(userUpdateDTO, user);
        user.setOffice(officeDao.findById(userUpdateDTO.getOfficeId()));
        user.getDocument().setDoc(docRepository.findByDocName(userUpdateDTO.getDocName()).get());
        user.setCountry(countryRepository.findByCountryCode(userUpdateDTO.getCitizenshipCode()).get());
        userDao.update(user, userUpdateDTO.getId());
    }

    @Transactional
    @Override
    public List<UserFilterOutDto> filter(UserFilterInDto userFilterInDTO) {
        List<User> list = userDao.filter(userFilterInDTO);
        return mapperFacade.mapAsList(list, UserFilterOutDto.class);
    }
}
