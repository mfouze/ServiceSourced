package fr.uvsq.datascale.web;

import fr.uvsq.datascale.api.command.DecreaseVolumeCommand;
import fr.uvsq.datascale.api.command.IncreaseVolumeCommand;
import fr.uvsq.datascale.replay.VolumeIncreasedReplayEventHandler;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.replay.ReplayingCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

    @Autowired
    @Qualifier("replayCluster")
    ReplayingCluster replayCluster;

    @Autowired
    VolumeIncreasedReplayEventHandler replayEventHandler;


    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "dadepo");
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }


    @RequestMapping("/decrease")
    @Transactional
    @ResponseBody
    public void doDecrease(@RequestParam("acc") String diplomeNumber, @RequestParam("amount") int volume) {
        DecreaseVolumeCommand dvc = new DecreaseVolumeCommand(diplomeNumber, volume);
        commandGateway.send(dvc);
    }

    @RequestMapping("/increase")
    @Transactional
    @ResponseBody
    public void doIncrease(@RequestParam("acc") String diplomeNumber, @RequestParam("amount") int volume) {
        IncreaseVolumeCommand ivc = new IncreaseVolumeCommand(diplomeNumber, volume);
        commandGateway.send(ivc);
    }

    @RequestMapping("/events")
    public String doReplay(Model model) {
        replayCluster.startReplay();
        model.addAttribute("events",replayEventHandler.getAudit());
        return "events";
    }
}
