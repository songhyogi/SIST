package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer{//설정파일 역할
	@Bean //컨테이너에 보관하는 역할
	public TilesConfigurer tilesConfigurer() {//메서드를 소문자로 만들면 된다.
		final TilesConfigurer configurer = new TilesConfigurer();//처음에 할당 후에는 바꾸지 않기 위해서 상수형태로 명시
		
		//해당 경로에 tiles.xml 파일을 넣음 (tiles를 사용하려면 tiles 설정파일이 필요하다.)
		configurer.setDefinitions(new String[] {"/WEB-INF/tiles-def/tilesdef.xml"});//타일스 설정파일을 지정하기 위해
		configurer.setCheckRefresh(true);
		return configurer;
	}
	@Bean
	public TilesViewResolver tilesViesResolver() {//메서드를 소문자로 만들면 된다.
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);//TilesView : 여러 개의 페이지를 조합하는 역할
		return tilesViewResolver;
	}
}
