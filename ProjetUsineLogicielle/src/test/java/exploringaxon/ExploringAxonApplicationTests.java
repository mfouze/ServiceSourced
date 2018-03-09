package exploringaxon;

import fr.uvsq.datascale.RunApp;
import fr.uvsq.datascale.api.command.IncreaseVolumeCommand;

import fr.uvsq.datascale.api.event.DiplomeCreatedEvent;
import fr.uvsq.datascale.api.event.VolumeIncreasedEvent;
import fr.uvsq.datascale.commandhandler.DecreaseVolumeHandler;
import fr.uvsq.datascale.commandhandler.IncreaseVolumeHandler;
import fr.uvsq.datascale.model.Diplome;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.h2.util.New;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RunApp.class)
@WebAppConfiguration
public class ExploringAxonApplicationTests {

	private FixtureConfiguration fixture;
	private String diplomeid;

	@Before
	public void setUp() {
		fixture = Fixtures.newGivenWhenThenFixture(Diplome.class);
		fixture.registerAnnotatedCommandHandler(new IncreaseVolumeHandler(fixture.getRepository()))
		.registerAnnotatedCommandHandler(new DecreaseVolumeHandler(fixture.getRepository()));
		diplomeid = "test-acc";
	}

	@Test
	public void testFirstDeposit() {
		fixture.given(new DiplomeCreatedEvent(diplomeid))
		.when(new IncreaseVolumeCommand(diplomeid, 10))
		.expectEvents(new VolumeIncreasedEvent(diplomeid, 10, 0));

	}

	//	@Test
	//	public void testFirstSecondDeposit() {
	//		fixture.given(new AccountCreatedEvent(accountNo),
	//					  new AccountCreditedEvent(accountNo, 100.00, 0.0))
	//			   .when(new CreditAccountCommand(accountNo, 40.00))
	//			   .expectEvents(new AccountCreditedEvent(accountNo, 40.00, 100.00));
	//	}
	//
	//	@Test
	//	public void testCreditingDebitingAndCrediting() {
	//		fixture.given(new AccountCreatedEvent(accountNo),
	//					  new AccountCreditedEvent(accountNo, 100.00, 0.0),
	//					  new AccountDebitedEvent(accountNo, 40.00, 100.0))
	//			   .when(new CreditAccountCommand(accountNo, 40.00))
	//			   .expectEvents(new AccountCreditedEvent(accountNo, 40.00, 60.00));
	//	}
	//
	//	@Test
	//	public void cannotCreditWithAMoreThanMillion() {
	//		fixture.given(new AccountCreatedEvent(accountNo))
	//			   .when(new CreditAccountCommand(accountNo, 10000000.00))
	//			   .expectException(IllegalArgumentException.class);
	//	}
	//
	//	@Test
	//	public void cannotDebitAccountWithZeroBalance() {
	//		fixture.given(new AccountCreatedEvent(accountNo))
	//			   .when(new DebitAccountCommand(accountNo, 1.0))
	//			   .expectException(IllegalArgumentException.class);
	//	}

}
