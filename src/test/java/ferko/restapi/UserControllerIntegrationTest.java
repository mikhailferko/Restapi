package ferko.restapi;

import ferko.restapi.dto.Data;
import ferko.restapi.dto.DataDto;
import ferko.restapi.dto.organization.OrganizationFilterInDto;
import ferko.restapi.dto.organization.OrganizationFilterOutDto;
import ferko.restapi.dto.organization.OrganizationSaveDto;
import ferko.restapi.dto.organization.OrganizationUpdateAndGetDto;
import ferko.restapi.dto.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveUserTest() throws Exception {
        UserSaveDto userSaveDto = new UserSaveDto(1, "Иван", "Иванов", "Иваныч", "Директор", "1234566", 21, "Паспорт", 12345L, null, 611, null);
        ResponseEntity<Data> response = restTemplate.postForEntity("/user/save", userSaveDto, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        assertThat(response.getBody().getData().toString(), is("{result=success}"));
    }

    @Test
    public void getUserTest() throws Exception {
        int id = 1;
        ResponseEntity<DataDto<UserGetDto>> response1 = restTemplate.exchange("/user/1",
                HttpMethod.GET, null, new ParameterizedTypeReference<DataDto<UserGetDto>>() {
                });
        DataDto<UserGetDto> data = response1.getBody();
        ResponseEntity<Data> response = restTemplate.getForEntity("/user/{id}", Data.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        assertThat(data.getData().getFirstName(), notNullValue());
        assertThat(data.getData().getSecondName(), notNullValue());
    }

    @Test
    public void updateUserTest() throws Exception {
        UserUpdateDto userUpdateDto = new UserUpdateDto(1, 2, "Иван", "Иванов", "Иванович", "директор", "89789999999", "Паспорт гражданина РФ", 12345L, null, 112, null);
        ResponseEntity<Data> response = restTemplate.postForEntity("/user/update", userUpdateDto, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        assertThat(response.getBody().getData().toString(), is("{result=success}"));
    }

    @Test
    public void listUserTest() throws Exception {
        UserFilterInDto userFilterInDto = new UserFilterInDto(1, "Иван", "Иванов", "Иванович", "qwertr", 21, 611);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<UserFilterInDto> entity = new HttpEntity<UserFilterInDto>(userFilterInDto, headers);
        ResponseEntity<DataDto<List<UserFilterOutDto>>> response1 = restTemplate.exchange("/user/list",
                HttpMethod.POST, entity, new ParameterizedTypeReference<DataDto<List<UserFilterOutDto>>>() {
                });
        DataDto<List<UserFilterOutDto>> data = response1.getBody();
        ResponseEntity<Data> response = restTemplate.postForEntity("/user/list", userFilterInDto, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        //if (data)
        assertThat(data.getData().get(0).getFirstName(), notNullValue());
        assertThat(data.getData().get(0).getId(), notNullValue());

    }
}
