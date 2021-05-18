package ferko.restapi.service.user;

import ferko.restapi.dao.country.CountryRepository;
import ferko.restapi.dao.doc.DocRepository;
import ferko.restapi.dao.document.DocumentDao;
import ferko.restapi.dao.office.OfficeDao;
import ferko.restapi.dao.user.UserDao;
import ferko.restapi.dto.user.*;
import ferko.restapi.exception.NotFoundException;
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
        if (officeDao.findById(userSaveDTO.getOfficeId()) != null) {
            user.setOffice(officeDao.findById(userSaveDTO.getOfficeId()));
        }
        else {
            throw new NotFoundException("Офиса с id = " + userSaveDTO.getOfficeId() + " не существует");
        }
        if (userSaveDTO.getCitizenshipCode() != 0) {
            if (countryRepository.findByCountryCode(userSaveDTO.getCitizenshipCode()).isPresent()) {
                user.setCountry(countryRepository.findByCountryCode(userSaveDTO.getCitizenshipCode()).get());
            } else {
                throw new NotFoundException("Страны с кодом " + userSaveDTO.getCitizenshipCode() + " не существует");
            }
        }
        if (user.getDocument() != null) {
            user.getDocument().setUser(user);
        }
        userDao.save(user);
        if (userSaveDTO.getDocCode() != 0 && user.getDocument() != null) {
            if (docRepository.findByDocCode(userSaveDTO.getDocCode()).isPresent()) {
                user.getDocument().setDoc(docRepository.findByDocCode(userSaveDTO.getDocCode()).get());
            } else {
                throw new NotFoundException("Документа с кодом " + userSaveDTO.getDocCode() + " не существует");
            }
        }
    }

    @Transactional
    @Override
    public void update(UserUpdateDto userUpdateDTO) {
        User user;
        if (userDao.findById(userUpdateDTO.getId()) != null) {
            user = userDao.findById(userUpdateDTO.getId());
        }
        else {
            throw new NotFoundException("Пользователя с id = " + userUpdateDTO.getId() + " не существует");
        }
        mapperFacade.map(userUpdateDTO, user);
        if (officeDao.findById(userUpdateDTO.getOfficeId()) != null) {
            user.setOffice(officeDao.findById(userUpdateDTO.getOfficeId()));
        }
        else {
            throw new NotFoundException("Офиса с id = " + userUpdateDTO.getOfficeId() + " не существует");
        }
        if (userUpdateDTO.getDocName() != null && user.getDocument() != null) {
            if (docRepository.findByDocName(userUpdateDTO.getDocName()).isPresent()) {
                user.getDocument().setDoc(docRepository.findByDocName(userUpdateDTO.getDocName()).get());
            }
            else {
                throw new NotFoundException("Документа с названием " + userUpdateDTO.getDocName() + " не существует");
            }
        }
        if (userUpdateDTO.getCitizenshipCode() != 0) {
            if (countryRepository.findByCountryCode(userUpdateDTO.getCitizenshipCode()).isPresent()) {
                user.setCountry(countryRepository.findByCountryCode(userUpdateDTO.getCitizenshipCode()).get());
            }
            else {
                throw new NotFoundException("Страны с кодом " + userUpdateDTO.getCitizenshipCode() + " не существует");
            }
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
