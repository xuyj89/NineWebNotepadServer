package top.muyan.ninewebnotepad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.muyan.ninewebnotepad.mapper")
public class NineWebNotepadApplication {

	public static void main(String[] args) {
		SpringApplication.run(NineWebNotepadApplication.class, args);
	}

}
