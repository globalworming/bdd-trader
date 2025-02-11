package net.bddtrader.apitests.clients;

import net.bddtrader.clients.Client;
import net.bddtrader.clients.ClientController;
import net.bddtrader.clients.ClientDirectory;
import net.bddtrader.config.TradingDataSource;
import net.bddtrader.portfolios.*;
import net.bddtrader.tradingdata.TradingData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static net.bddtrader.config.TradingDataSource.DEV;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class WhenDifferentTypesOfClientsRegister {

    ClientDirectory clientDirectory = new ClientDirectory();
    PortfolioDirectory portfolioDirectory = new PortfolioDirectory();
    PortfolioController portfolioController = new PortfolioController(TradingDataSource.DEV, portfolioDirectory);
    ClientController controller = new ClientController(clientDirectory, portfolioController);

    public WhenDifferentTypesOfClientsRegister(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"Sarah-Jane",     "Smith"},
                {"Bill",  "Oddie"},
                {"Tim", "Brooke-Taylor"},
        });
    }

    private final String firstName;
    private final String lastName;


    @Before
    public void resetTestData() {
        TradingData.instanceFor(DEV).reset();
    }

    @Test
    public void aClientRegisters() {

        // WHEN
        Client registeredClient = controller.register(Client.withFirstName(firstName).andLastName(lastName).andEmail("sarah-jane@smith.com"));

        // THEN
        assertThat(registeredClient).isEqualToComparingFieldByField(registeredClient);
    }


}
