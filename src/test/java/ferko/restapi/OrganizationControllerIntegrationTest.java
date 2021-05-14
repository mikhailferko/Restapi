package ferko.restapi;

import ferko.restapi.dto.Data;
import ferko.restapi.dto.DataDto;
import ferko.restapi.dto.organization.OrganizationFilterInDto;
import ferko.restapi.dto.organization.OrganizationFilterOutDto;
import ferko.restapi.dto.organization.OrganizationSaveDto;
import ferko.restapi.dto.organization.OrganizationUpdateAndGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;



@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveOrganizationTest() throws Exception {
        OrganizationSaveDto organizationSaveDto = new OrganizationSaveDto("Qwer", "asdf", "1234", "2345", "lkjh", null, null);
        ResponseEntity<Data> response = restTemplate.postForEntity("/organization/save", organizationSaveDto, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        assertThat(response.getBody().getData().toString(), is("{result=success}"));
    }

    @Test
    public void getOrganizationTest() throws Exception {
        int id = 1;
        ResponseEntity<DataDto<OrganizationUpdateAndGetDto>> response1 = restTemplate.exchange("/organization/1",
                HttpMethod.GET, null, new ParameterizedTypeReference<DataDto<OrganizationUpdateAndGetDto>>() {
                });
                DataDto<OrganizationUpdateAndGetDto> data = response1.getBody();
        ResponseEntity<Data> response = restTemplate.getForEntity("/organization/{id}", Data.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        assertThat(data.getData().getName(), notNullValue());
        assertThat(data.getData().getFullName(), notNullValue());
    }

    @Test
    public void updateOrganizationTest() throws Exception {
        OrganizationUpdateAndGetDto organizationUpdateAndGetDto = new OrganizationUpdateAndGetDto(1, "Qwer", "asdf", "1234", "2345", "lkjh", null, null);
        ResponseEntity<Data> response = restTemplate.postForEntity("/organization/update", organizationUpdateAndGetDto, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        assertThat(response.getBody().getData().toString(), is("{result=success}"));
    }

    @Test
    public void listOrganizationTest() throws Exception {
        OrganizationFilterInDto organizationFilterInDto = new OrganizationFilterInDto("Ромашка", null, null);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<OrganizationFilterInDto> entity = new HttpEntity<OrganizationFilterInDto>(organizationFilterInDto, headers);
        ResponseEntity<DataDto<List<OrganizationFilterOutDto>>> response1 = restTemplate.exchange("/organization/list",
                HttpMethod.POST, entity, new ParameterizedTypeReference<DataDto<List<OrganizationFilterOutDto>>>() {
                });
        DataDto<List<OrganizationFilterOutDto>> data = response1.getBody();
        ResponseEntity<Data> response = restTemplate.postForEntity("/organization/list", organizationFilterInDto, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getClass(), is(Data.class));
        assertThat(data.getData().get(0).getName(), notNullValue());
        assertThat(data.getData().get(0).getId(), notNullValue());

    }
}
