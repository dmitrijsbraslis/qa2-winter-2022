package pageobject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.model.FlightInfo;
import pageobject.model.Passenger;
import pageobject.pages.HomePage;
import pageobject.pages.PassengerInfoPage;
import pageobject.pages.SeatSelectionPage;
import pageobject.pages.SuccessfulRegistrationPage;

public class TicketsTestsOnPages {
    private final String URL = "qaguru.lv:8089/tickets/";

    private BaseFunc baseFunc = new BaseFunc();

    @Test
    public void successfulRegistrationTest() {
        Passenger passenger = new Passenger("TestName", "TestSurname");
        int seatNr = RandomUtils.nextInt(1, 35);

        FlightInfo info = new FlightInfo("SFO", "RIX", "CCCCCC", 2, 1, 4,
                "16-05-2018", seatNr);
        info.setPassenger(passenger);

        baseFunc.openUrl(URL);
        HomePage homePage = new HomePage(baseFunc);
        homePage.selectAirports(info.getDeparture(), info.getDestination());

        PassengerInfoPage infoPage = new PassengerInfoPage(baseFunc);
        infoPage.fillInPassengerInfo(info);

        Assertions.assertEquals(passenger.getFirstName(), infoPage.getPassengerName(), "Wrong name!");
        Assertions.assertEquals(info.getDeparture(), infoPage.getFirstFromAirport(), "Error msg!");
        Assertions.assertEquals(info.getDeparture(), infoPage.getSecondFromAirport(), "Error msg!");
        Assertions.assertEquals(info.getDestination(), infoPage.getFirstToAirport(), "Error msg!");
        Assertions.assertEquals(info.getDestination(), infoPage.getSecondToAirport(), "Error msg!");

        Assertions.assertTrue(infoPage.getPrice().length() > 0, "Error message!");

        infoPage.book();

        SeatSelectionPage seatSelectionPage = new SeatSelectionPage(baseFunc);
        seatSelectionPage.selectSeat(seatNr);

        int selectedSeat = seatSelectionPage.getSelectedSeatNr();
        Assertions.assertEquals(seatNr, selectedSeat, "Wrong seat selected");

        seatSelectionPage.book();

        SuccessfulRegistrationPage successfulRegistrationPage = new SuccessfulRegistrationPage(baseFunc);
        Assertions.assertTrue(successfulRegistrationPage.isSuccessfulRegistrationTextAppears(),
                "Wrong text on successful registration page");
    }
}
