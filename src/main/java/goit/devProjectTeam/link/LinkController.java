package goit.devProjectTeam.link;

import goit.devProjectTeam.entity.Link;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1")
public class LinkController {

    public static final String ALL_LINKS = "redirect:/v1/allLinks";
    private final LinkService linkService;

    @GetMapping("/token/{token}")
    public ModelAndView redirectByToken(@PathVariable String token) {
        Link linkByToken = linkService.getLinkByToken(token);
        linkService.validateLinkDoNotExpired(linkByToken);
        linkService.increaseClickCounter(linkByToken.getLinkId());
        return new ModelAndView("redirect:" + linkByToken.getLongLink());
    }

    @GetMapping("/link/create")
    public ModelAndView getCreatePage(Link link) {
        ModelAndView modelAndView = new ModelAndView("createLink");
        return modelAndView.addObject("link", link);
    }

    @PostMapping(value = "/link/create")
    public ModelAndView createJson(@Valid @ModelAttribute("link") Link link) {
        ModelAndView modelAndView = new ModelAndView(ALL_LINKS);
        linkService.add(link);
        return modelAndView;
    }

    @GetMapping(value = "/allLinks")
    public ModelAndView getAllLinks() {
        ModelAndView modelAndView = new ModelAndView("allLinks");
        modelAndView.addObject("links", linkService.listAllForUser());
        return modelAndView;
    }

    @GetMapping(value = "/activeLinks")
    public ModelAndView getActiveLinks() {
        ModelAndView modelAndView = new ModelAndView("activeLinks");
        modelAndView.addObject("activeLinks",
                linkService.findAllMoreThenExpirationDate(new Timestamp(System.currentTimeMillis())));
        return modelAndView;
    }

    @RequestMapping("/link/delete/{linkId}")
    public ModelAndView delete(@PathVariable("linkId") long linkId) {
        ModelAndView modelAndView = new ModelAndView(ALL_LINKS);
        linkService.deleteById(linkId);
        return modelAndView;
    }

    @GetMapping("/link/edit")
    public ModelAndView edit(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Link link = linkService.getById(id);
        modelAndView.addObject("link", link);
        return modelAndView;
    }

    @PostMapping("/link/edit")
    public ModelAndView editLink(@ModelAttribute("link") Link link) {
        ModelAndView modelAndView = new ModelAndView(ALL_LINKS);
        linkService.updateLink(link);
        return modelAndView;
    }

}
