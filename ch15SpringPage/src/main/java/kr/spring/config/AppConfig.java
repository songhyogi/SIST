package kr.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kr.spring.interceptor.AutoLoginCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.interceptor.WriterCheckInterceptor;
import kr.spring.websocket.SocketHandler;

//자바코드 기반 설정 클래스
@Configuration
@EnableWebSocket
public class AppConfig implements WebMvcConfigurer,WebSocketConfigurer{
	private LoginCheckInterceptor loginCheck;
	private WriterCheckInterceptor writerCheck;
	private AutoLoginCheckInterceptor autoLoginCheck;
	
	@Bean
	public AutoLoginCheckInterceptor interceptor() {
		autoLoginCheck = new AutoLoginCheckInterceptor();
		return autoLoginCheck;
	}
	
	@Bean //객체 생성해서 컨테이너에 보관
	public LoginCheckInterceptor interceptor2() {
		loginCheck = new LoginCheckInterceptor();
		return loginCheck;
	}
	
	@Bean
	public WriterCheckInterceptor interceptor4() {
		writerCheck = new WriterCheckInterceptor();
		return writerCheck;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {//이 메서드 내에 인터셉터 등록
		//AutoLoginCheckInterceptor 설정 - 모든 경우에 autoLoginCheckInterceptor가 동작되게 했지만 이미지,css,js 호출은 로그인과 관계가 없기 때문에 오토인터셉터가 동작하지 않게 설정
		registry.addInterceptor(autoLoginCheck)
				.addPathPatterns("/**")
				.excludePathPatterns("/images/**")
				.excludePathPatterns("/image_upload/**")
				.excludePathPatterns("/upload/**")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/js/**")
				.excludePathPatterns("/member/login")
				.excludePathPatterns("/member/logout");
		//LoginCheckInterceptor 설정
		registry.addInterceptor(loginCheck)//인터셉터 등록
				  .addPathPatterns("/member/myPage")//myPage를 요청했을 때 인터셉터가 로그인 여부 체크
				  .addPathPatterns("/member/update")
				  .addPathPatterns("/member/changePassword")
				  .addPathPatterns("/member/delete")
				  .addPathPatterns("/board/write")//부모글은 인터셉터로 하지만 자식글은 ajax통신을 하기 때문에 여기에 넣으면 안 된다.
				  .addPathPatterns("/board/update")
				  .addPathPatterns("/board/delete")
				  .addPathPatterns("/talk/talkRoomWrite")
				  .addPathPatterns("/talk/talkList")
				  .addPathPatterns("/talk/talkDetail");
		//WriterCheckInterceptro 설정
		registry.addInterceptor(writerCheck)
				.addPathPatterns("/board/update")
				.addPathPatterns("/board/delete");
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		//XML 설정 파일 경로 지정
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/member.xml",
				"/WEB-INF/tiles-def/board.xml",
				"/WEB-INF/tiles-def/talk.xml"
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	@Bean //TilesViewResolver는 뷰의 경로,확장자 경로인데 뷰를 호출해주는 역할을 함
	public TilesViewResolver tilesViesResolver() {//여기서의 TilesViewResolver는 ->25이동
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
				//뷰 지정                     //여러개를 하나로 조합하는건 TilesView가 담당
		tilesViewResolver.setViewClass(TilesView.class);//TilesView가 경로의 정보를 가지고 있는데 이 경로를 호출해주는 역할을 함	
		return tilesViewResolver;
	}
	
	@Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
    	Properties prop = new Properties();
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.transport.protocol", "smtp");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.debug", "true");
    	
    	JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
    	javaMail.setHost("smtp.gmail.com");
    	javaMail.setPort(587);
    	javaMail.setDefaultEncoding("utf-8");
    	javaMail.setUsername("dragonyxit@gmail.com");
    	javaMail.setPassword("zgnwhuaalygaqyfm");
    	javaMail.setJavaMailProperties(prop);
    	return javaMail;
    }
	
	//웹소켓 셋팅
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketHandler(), "message-ws").setAllowedOrigins("*");//객체 생성한 후 메서드 넣어주기
	}
}
