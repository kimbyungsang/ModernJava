package com.example.thuva.api.controller;

import com.example.thuva.api.entity.AddressEntity;
import com.example.thuva.api.exceptions.RestApiErrorHandler;
import com.example.thuva.api.hateoas.AddressRepresentationModelAssembler;
import com.example.thuva.api.model.AddAddressReq;
import com.example.thuva.api.model.Address;
import com.example.thuva.api.service.AddressService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.thuva.api.AppConfig;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    private static final String URI = "/api/v1/addresses";
    private static final String id = "a1b9b31d-e73c-4112-af7c-b68530f38222";
    private static MockMvc mockMvc;
    private static MessageSource messageSource = mock(MessageSource.class);
    private static AddressService service = mock(AddressService.class);
    private static AddressController controller;
    private static AddressEntity entity;
    private static Address address;
    private static AddAddressReq addAddressReq;
    private JacksonTester<Address> addressJacksonTester;
    private JacksonTester<AddAddressReq> addAddressReqJacksonTester;

    @BeforeAll
    public static void setup() {
        controller = new AddressController(service, new AddressRepresentationModelAssembler());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestApiErrorHandler(messageSource))
                .alwaysDo(print())
                .build();

        entity = new AddressEntity()
                .setId(UUID.fromString(id))
                .setNumber("111")
                .setStreet("Street")
                .setCity("City")
                .setState("State")
                .setCountry("Country")
                .setPincode("12345");
        addAddressReq = new AddAddressReq()
                .id(entity.getId().toString())
                .number(entity.getNumber())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .pincode(entity.getPincode());
    }

    @BeforeEach
    public void configureJsonTester() {
        JacksonTester.initFields(this, new AppConfig().objectMapper());
    }
    /*
    @Test
    @DisplayName("returns address by given existing ID")
    public void getAddressByOrderIdWhenExists() throws Exception {

    }*/

    private void verifyJson(final ResultActions result) throws Exception {
        final String BASE_PATH = "http://localhost";
        result
                .andExpect(jsonPath("id", is(entity.getId().toString())))
                .andExpect(jsonPath("number", is(entity.getNumber())))
                .andExpect(jsonPath("street", is(entity.getStreet())))
                .andExpect(jsonPath("city", is(entity.getCity())))
                .andExpect(jsonPath("state", is(entity.getState())))
                .andExpect(jsonPath("country", is(entity.getCountry())))
                .andExpect(jsonPath("pincode", is(entity.getPincode())))
                .andExpect(jsonPath("links[0].rel", is("self")))
                .andExpect(jsonPath("links[0].href", is(BASE_PATH + "/" + entity.getId())))
                .andExpect(jsonPath("links[1].rel", is("self")))
                .andExpect(jsonPath("links[1].href", is(BASE_PATH + URI + "/" + entity.getId())));
    }

    @Test
    @DisplayName("returns newly created address with CREATED status")
    public void createAddress() throws Exception {
        given(service.createAddressByReq(addAddressReq))
                .willReturn(Optional.of(entity));

        // when
        ResultActions result = mockMvc.perform(
                post("/api/v1/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addAddressReqJacksonTester.write(addAddressReq).getJson())
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect((status().isCreated()));
        verifyJson(result);
    }

    @Test
    @DisplayName("return")
    public void getAddressByID() throws Exception {

    }
}
