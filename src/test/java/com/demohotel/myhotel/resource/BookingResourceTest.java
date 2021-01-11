package com.demohotel.myhotel.resource;

import com.demohotel.myhotel.domain.Bonus;
import com.demohotel.myhotel.domain.User;
import com.demohotel.myhotel.repository.*;
import com.demohotel.myhotel.service.BonusService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.activation.DataSource;
import java.io.IOException;

import static com.demohotel.myhotel.prototypes.BookingPrototypes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingResourceTest {

    @MockBean
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private BookingLedgerRepository bookingLedgerRepository;

    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Autowired
    private RoomDetailRepository roomDetailRepository;

    MockMvc bookingResourceMockMvc;

    @Autowired
    private BonusService bonusService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        cleanDB();
        ReflectionTestUtils.setField(bonusService, "userRepository", userRepository);
        ReflectionTestUtils.setField(bonusService, "bonusRepository", bonusRepository);
        ReflectionTestUtils.setField(bonusService, "bookingLedgerRepository", bookingLedgerRepository);
        ReflectionTestUtils.setField(bonusService, "roomPriceRepository", roomPriceRepository);
        BookingResource bookingResource = new BookingResource(bonusService);
        objectMapper = new ObjectMapper();
        bookingResourceMockMvc = MockMvcBuilders
                .standaloneSetup(bookingResource)
                .build();
    }


    @Test
    public void bookRoom() throws Exception {
//        when(bonusService.bookRoom(any())).thenReturn(aBookingLedger());
        roomDetailRepository.save(aRoomDetail());
        roomPriceRepository.save(aRoomPrice());
        User savedUser = userRepository.save(aUser());
        Bonus bonus = new Bonus(savedUser.getId(), 2000);
        bonusRepository.save(bonus);


        bookingResourceMockMvc.perform(post("/bonus/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(aBookingDTO().setUserId(savedUser.getId()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomNo").value("10"))
                .andExpect(jsonPath("$.roomType").value("DELUXE"))
                .andExpect(jsonPath("$.bookStatus").value("BOOKED"));
    }

    @Test
    public void bookRoomWithLessBonusPoint() throws Exception {
//        when(bonusService.bookRoom(any())).thenReturn(aBookingLedger());
        roomDetailRepository.save(aRoomDetail());
        roomPriceRepository.save(aRoomPrice());
        User savedUser = userRepository.save(aUser());
        Bonus bonus = new Bonus(savedUser.getId(), 1000);
        bonusRepository.save(bonus);


        bookingResourceMockMvc.perform(post("/bonus/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(aBookingDTO().setUserId(savedUser.getId()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomNo").value("10"))
                .andExpect(jsonPath("$.roomType").value("DELUXE"))
                .andExpect(jsonPath("$.bookStatus").value("PENDING_APPROVAL"));
    }

    @Test
    public void bookRoomWithZeroBonusPoint() throws Exception {
//        when(bonusService.bookRoom(any())).thenReturn(aBookingLedger());
        roomDetailRepository.save(aRoomDetail());
        roomPriceRepository.save(aRoomPrice());
        User savedUser = userRepository.save(aUser());
        Bonus bonus = new Bonus(savedUser.getId(), 0);
        bonusRepository.save(bonus);


        bookingResourceMockMvc.perform(post("/bonus/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(aBookingDTO().setUserId(savedUser.getId()))))
                .andExpect(status().isBadRequest());
    }

    private void cleanDB() {
        userRepository.deleteAll();
        bonusRepository.deleteAll();
        bookingLedgerRepository.deleteAll();
        roomPriceRepository.deleteAll();
        roomDetailRepository.deleteAll();
    }

    public static byte[] convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        return mapper.writeValueAsBytes(object);
    }


}