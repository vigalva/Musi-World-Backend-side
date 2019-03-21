package smartspace.data;

import smartspace.data.Location;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.profiles.active=default"})
public class LocationTests {
	@Test
	public void testLocatoins() {
		//GIVEN initialized Location
		Location location = new Location();
		double max = 50;
		for(double x=-max;x<max;x+=0.01)
			for(double y=-max;y<max;y+=0.01) {
				location.setX(x);
				location.setY(y);
				assertThat(location.getX()).isEqualTo(x);
				assertThat(location.getY()).isEqualTo(y);

			}
	}
}
