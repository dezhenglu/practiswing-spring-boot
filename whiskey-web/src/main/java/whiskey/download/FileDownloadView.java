package whiskey.download;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component("downloader")
public class FileDownloadView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filename = (String) model.get("download-filename");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(filename, StandardCharsets.UTF_8.name()));
        response.setContentType("text/plain");

        ServletOutputStream outputStream = response.getOutputStream();
        String content = (String) model.get("download-content");
        StreamUtils.copy(content, StandardCharsets.UTF_8, outputStream);
    }
}
