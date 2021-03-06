package ferko.restapi.service.user;

import ferko.restapi.dao.country.CountryRepository;
import ferko.restapi.dao.doc.DocRepository;
import ferko.restapi.dao.document.DocumentDao;
import ferko.restapi.dao.office.OfficeDao;
import ferko.restapi.dao.user.UserDao;
import ferko.restapi.dto.user.*;
import ferko.restapi.exception.NotFoundException;
import ferko.restapi.mapper.MapperFacade;
import ferko.restapi.model.*;
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
        UserGetDto dto = mapperFacade.map(user, UserGetDto.class);
        if (dto == null){
            throw new NotFoundException("Пользователя с id = " + id + " не существует");
        }
        dto.setDocName(documentDao.findById(id).getDoc().getDocName());
        dto.setCitizenshipName(user.getCountry().getCountryName());
        dto.setCitizenshipCode(user.getCountry().getCountryCode());
        return dto;
    }

    @Transactional
    @Override
    public void save(UserSaveDto userSaveDTO) {
        User user = mapperFacade.map(userSaveDTO, User.class);

        int officeId = userSaveDTO.getOfficeId();
        Office office = officeDao.findById(officeId);

        if (office != null) {
            user.setOffice(office);
        }
        else {
            throw new NotFoundException("Офиса с id = " + officeId + " не существует");
        }

        int citizenshipCode = userSaveDTO.getCitizenshipCode();

        if (citizenshipCode != 0) {
            Country country = countryRepository.findByCountryCode(citizenshipCode)
                    .orElseThrow(() -> new NotFoundException("Страны с кодом " + citizenshipCode + " не существует"));
                user.setCountry(country);
        }

        Document document = user.getDocument();

        if (document != null) {
            document.setUser(user);
        }
        userDao.save(user);

        int docCode = userSaveDTO.getDocCode();

        if (docCode != 0 && document != null) {
            Doc doc = docRepository.findByDocCode(docCode)
                    .orElseThrow(() -> new NotFoundException("Документа с кодом " + docCode + " не существует"));
                document.setDoc(doc);
        }
    }

    @Transactional
    @Override
    public void update(UserUpdateDto userUpdateDTO) {
        User user = userDao.findById(userUpdateDTO.getId());
        if (user == null) {
            throw new NotFoundException("Пользователя с id = " + userUpdateDTO.getId() + " не существует");
        }
        mapperFacade.map(userUpdateDTO, user);

        int officeId = userUpdateDTO.getOfficeId();
        Office office = officeDao.findById(officeId);

        if (office != null) {
            user.setOffice(office);
        }
        else {
            throw new NotFoundException("Офиса с id = " + officeId + " не существует");
        }

        String docName = userUpdateDTO.getDocName();
        Document document = user.getDocument();

        if (docName != null && document != null) {
            Doc doc = docRepository.findByDocName(docName)
                    .orElseThrow(() -> new NotFoundException("Документа с названием " + docName + " не существует"));
                document.setDoc(doc);
        }

        int citizenshipCode = userUpdateDTO.getCitizenshipCode();

        if (citizenshipCode != 0) {
            Country country = countryRepository.findByCountryCode(citizenshipCode)
                    .orElseThrow(() -> new NotFoundException("Страны с кодом " + citizenshipCode + " не существует"));
                user.setCountry(country);
        }
        userDao.update(user, userUpdateDTO.getId());
    }

    @Transactional
    @Override
    public List<UserFilterOutDto> filter(UserFilterInDto userFilterInDTO) {
        List<User> list = userDao.filter(userFilterInDTO);
        return mapperFacade.mapAsList(list, UserFilterOutDto.class);
    }
}
