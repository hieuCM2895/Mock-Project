package fa.training.impsystem.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fa.training.impsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment env;

    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager Bean
        return super.authenticationManagerBean();
    }

    @Bean
    @Lazy
    public ModelMapper createModelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    @Lazy
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", env.getProperty("cloud.cloud_name"),
                        "api_key", env.getProperty("cloud.api_key"),
                        "api_secret", env.getProperty("cloud.api_secret"),
                        "secure", true));
        return cloudinary;
    }

    @Bean
    @Lazy
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(env.getProperty("email.name"));
        mailSender.setPassword(env.getProperty("email.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/registration/**", "/js/**", "/forgot-password/**", "/expired/**",
                                "/css/**", "/img/**", "/reset-password/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(userLoginSuccessHandler)
                .failureHandler(userLoginFailureHandler)
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(7 * 24 * 60 * 60)
                .userDetailsService(userService)
                .useSecureCookie(true)
                .and()
                .logout()
                .deleteCookies("remember-me")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(appLogoutSuccessHandler())
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")
                .sessionFixation()
                .newSession()
                .maximumSessions(1)
                .expiredUrl("/login")
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
    }

    @Bean
    @Lazy
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
        tokenRepo.setDataSource(dataSource);
        return tokenRepo;
    }

    @Bean
    @Lazy
    protected LogoutSuccessHandler appLogoutSuccessHandler() {
        return new UserLogoutSuccessHandler();
    }

    @Bean
    @Lazy
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SessionTimeoutHandler httpSessionEventPublisher() {
        return new SessionTimeoutHandler();
    }

}
