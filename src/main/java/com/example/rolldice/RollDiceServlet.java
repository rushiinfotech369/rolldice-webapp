package com.example.rolldice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "RollDiceServlet", urlPatterns = {"/roll"})
public class RollDiceServlet extends HttpServlet {
    private static final Random RANDOM = new Random();

    private int rollDice(int sides) {
        return RANDOM.nextInt(sides) + 1;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String sidesParam = req.getParameter("sides");
        int sides = 6;
        if (sidesParam != null) {
            try {
                sides = Integer.parseInt(sidesParam);
                if (sides < 1) sides = 6;
            } catch (NumberFormatException ignored) { sides = 6; }
        }

        String rollNow = req.getParameter("roll");
        Integer result = null;
        if (rollNow != null) {
            result = rollDice(sides);
        }

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!doctype html>");
            out.println("<html><head><meta charset='utf-8'><title>RollDice WebApp</title>");
            out.println("<style>body{font-family:Arial,Helvetica,sans-serif;margin:2rem;} .card{padding:1rem;border-radius:8px;background:#f7f7f7;width:320px;} button{padding:0.5rem 1rem;margin-top:0.5rem;}</style>");
            out.println("</head><body>");
            out.println("<div class='card'>");
            out.println("<h2>RollDice WebApp</h2>");
            out.println("<form method='get' action='roll'>");
            out.println("Number of sides: <input type='number' name='sides' value='" + sides + "' min='1'/> <br/>");
            out.println("<button type='submit' name='roll' value='1'>Roll</button>");
            out.println("</form>");
            if (result != null) {
                out.println("<p><strong>🎲 You rolled: " + result + " (1-" + sides + ")</strong></p>");
            } else {
                out.println("<p>Press <em>Roll</em> to generate a random number.</p>");
            }
            out.println("</div>");
            out.println("</body></html>");
        }
    }
}
