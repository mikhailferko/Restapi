package ferko.restapi.mapper;

import ferko.restapi.dto.user.UserGetDto;
import ferko.restapi.dto.user.UserSaveDto;
import ferko.restapi.dto.user.UserUpdateDto;
import ferko.restapi.model.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false)
                .constructorResolverStrategy(null)
                .build();
        mapperFactory.classMap(UserUpdateDto.class, User.class)
                .field("docNumber", "document.documentNumber")
                .field("docDate", "document.documentDate").byDefault().register();

        mapperFactory.classMap(User.class, UserGetDto.class)
                .field("document.documentNumber", "docNumber")
                .field("document.documentDate", "docDate").byDefault().register();

        mapperFactory.classMap(UserSaveDto.class, User.class)
                .field("docNumber", "document.documentNumber")
                .field("docDate", "document.documentDate").byDefault().register();

        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return DefaultMapperFactory.class;
    }

}
