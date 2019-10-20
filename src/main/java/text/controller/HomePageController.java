package text.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import text.domain.GenService;
import text.domain.Text;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.io.FileUtils.getFile;


@Controller
public class HomePageController {

	@Autowired
	private GenService genService;


	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String textRe(Model model, HttpServletRequest request) {
		Text text = (Text) model.asMap().get("text");
		if (text != null) {
			if (text.getTextas1().length() > 0) {
				model.addAttribute("pirmas", text.getTextas1());
				model.addAttribute("pirmasRez", genService.list(text.getTextas1()));
			}
			if (text.getTextas2().length() > 0) {
				model.addAttribute("antras", text.getTextas2());
				model.addAttribute("antrasRez", genService.list(text.getTextas2()));
			}
			if (!text.getFile().isEmpty()) {
				model.addAttribute("uploudas", genService.list(genService.readFromUpload(request, text.getFile())));
			}
		}
		model.addAttribute("pasisveikinimas", "Sveiki vartotojai !");
		model.addAttribute("modelForma", new Text());
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String forma(@ModelAttribute("modelForma") Text text, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		List<List<String>> lists = new ArrayList<>();
		if (text.getTextas1().length() > 0) {
			lists.add(genService.list(text.getTextas1()));
		}
		if (text.getTextas2().length() > 0) {
			lists.add(genService.list(text.getTextas2()));
		}

		genService.multipartFile(request, text.getFile());

		if (!text.getFile().isEmpty()) {
			lists.add(genService.list(genService.readFromUpload(request, text.getFile())));
		}
		genService.writeToFileCurrent(lists, request);
		genService.writeToFileAudit(lists, request);
		redirectAttrs.addFlashAttribute("text", text);
		return "redirect:/";
	}

	@RequestMapping(value = {"/view"}, method = RequestMethod.GET)
	public String textAuditView(Model model, HttpServletRequest request) {
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		File file = new File(rootDirectory + "res/auditas.txt");
		String content = null;
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		LinkedList<String> listView = genService.fileViewList(content);
		model.addAttribute("content", listView);
		return "viewaudit";
	}


	@RequestMapping(value = "/download/{filename}", method = RequestMethod.GET)
	public @ResponseBody
	HttpEntity<byte[]> downloadB(HttpServletRequest request, @PathVariable("filename") String fileName) throws IOException {
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		File file = getFile(rootDirectory + "res/" + fileName + ".txt");
		byte[] document = FileCopyUtils.copyToByteArray(file);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "txt"));
		header.set("Content-Disposition", "inline; filename=" + file.getName());
		header.setContentLength(document.length);
		return new HttpEntity<byte[]>(document, header);
	}

}
