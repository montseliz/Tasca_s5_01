package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flowers WebClient API", version = "1.0.0"))
public class S05T01N03LizMontseApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N03LizMontseApplication.class, args);
	}

}
