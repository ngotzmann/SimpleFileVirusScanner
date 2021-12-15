package net.ngotzmann.SimpleFileVirusScan.file;

import net.ngotzmann.SimpleFileVirusScan.virusscanner.ScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.ConnectException;

/**
 * Controller file handling
 */
@Controller
public class FileController {

    @Autowired
    public FileService fileService;

    @RequestMapping(value = "/scan", method = RequestMethod.POST)
    public String handleFileUpload(@PathVariable("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            ScanResult result = fileService.isFileInfected(file);
            redirectAttributes = setResultText(result, redirectAttributes, file.getOriginalFilename());
        } catch (IllegalStateException | IllegalArgumentException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("ExceptionMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("ExceptionMessage",
                    "We are sorry this operation failed, Please make sure that no binary was uploaded.");
        }
        return "redirect:/";
    }

    private RedirectAttributes setResultText(ScanResult result, RedirectAttributes redirectAttributes, String fileName) {
        if (result == null) {
            return redirectAttributes.addFlashAttribute("ExceptionMessage", "Can not check file");
        }
        if (result.isFileInfected()) {
            return redirectAttributes.addFlashAttribute("fileIsInfected",
                    "Your file: " + fileName + " is infected!");
        } else {
            return redirectAttributes.addFlashAttribute("fileNotIsInfected",
                    "Your file: " + fileName + " is not infected");
        }
    }
}
