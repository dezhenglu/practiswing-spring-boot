package delta;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;

@Configuration
public class FtpConfig {

    @ConfigurationProperties(prefix = "delta.ftp")
    @Bean
    SessionFactory<FTPFile> ftpSessionFactory() {
        return new DefaultFtpSessionFactory();
    }


    @Bean
    FtpRemoteFileTemplate ftpRemoteFileTemplate() {
        FtpRemoteFileTemplate template = new FtpRemoteFileTemplate(ftpSessionFactory());
        template.setRemoteDirectoryExpression(new LiteralExpression("/contents"));
        return template;
    }
}
