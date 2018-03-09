package fr.uvsq.datascale.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;


@Controller
public class ViewController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "/view", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Map<String, Double>> getAccounts() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Double>> queryResult = jdbcTemplate.query("SELECT * from diplome_view ORDER BY diplome_id", (rs, rowNum) -> {
            return new HashMap<String, Double>() {{
                put(rs.getString("DIPLOME_ID"),rs. getDouble("VOL_HORAIRE"));
            }};
        });

        return queryResult;
    }
}
