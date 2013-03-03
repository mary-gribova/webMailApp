package webMailAppEE;

import webMailApp.dao.LetterDAO;
import webMailApp.dto.FolderDTO;
import webMailApp.dto.LetterDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Iterator;

public class LetterServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        StringBuilder builder = new StringBuilder();
        builder.append("<h2>Hello user!!</h2>");
        builder.append("<table border=1 width=50% height=50%>");
        builder.append("<tr><th>From</th><th>Theme</th><th>Date</th><tr>");

        List<FolderDTO> folders = new LetterDAO().getRecievedLetters("user");
        List<LetterDTO> letters = folders.get(0).getLetters();
        Iterator iterator = letters.iterator();

        while (iterator.hasNext()) {
            LetterDTO l = (LetterDTO) iterator.next();
            builder.append("<tr><th>");
            builder.append(l.getLetterFrom());
            builder.append("</th><th>");
            builder.append(l.getLetterTheme());
            builder.append("</th><th>");
            builder.append(l.getLetterDate().toString());
            builder.append("</th><tr>");
        }

        builder.append("</table>");

        out.write(builder.toString());
    }
}
