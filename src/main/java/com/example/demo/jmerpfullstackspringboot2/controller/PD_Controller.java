package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.*;
import com.example.demo.jmerpfullstackspringboot2.form.PD_BOMCreateForm;
import com.example.demo.jmerpfullstackspringboot2.form.PD_CostCreateForm;
import com.example.demo.jmerpfullstackspringboot2.form.PD_QCCreateForm;
import com.example.demo.jmerpfullstackspringboot2.form.PD_WOCreateForm;
import com.example.demo.jmerpfullstackspringboot2.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/PD")
public class PD_Controller {

    private final PD_BOMService bomservice;
    private final PD_WOService woservice;
    private final PD_WorkHistoryService whservice;
    private final PD_RSService rsservice;
    private final PD_QCService qcservice;
    private final PD_CostService costservice;
    private final PC_PurchaseService purchaseservice;
    private final MG_acoountService accountMg;

    // --------------------- PD_BOM -------------------------------//
    @GetMapping("/bom")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<PD_BOM> paging = bomservice.getList(page);
        model.addAttribute("paging", paging);
        return "pd/PD_bom";
    }

    @GetMapping("/bom/regi")
    public String regi(PD_BOMCreateForm bomcreateform) {
        return "pd/PD_BOMregi";
    }

    @PostMapping("/bom/regi")
    public String regi(@Valid PD_BOMCreateForm bomcreateform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pd/PD_BOMregi";
        }

        bomservice.create(bomcreateform.getProdCode(), bomcreateform.getProdName(),
                bomcreateform.getRawMatNum(), bomcreateform.getUnit(), bomcreateform.getNum(),
                bomcreateform.getType(), bomcreateform.getRawNum(), bomcreateform.getWorkTime());


        return "redirect:/PD/bom";
    }

    // -------------------- PD_WO ----------------------------------//

    @GetMapping("/wo")
    public String WO_list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<PD_WO> paging = woservice.getList(page);
        model.addAttribute("paging", paging);
        return "pd/PD_WO";
    }

    @GetMapping("/wo/regi")
    public String WO_regi(PD_WOCreateForm wocreateform) {
        return "pd/PD_WOregi";
    }

    @PostMapping("/wo/regi")
    public String WO_regi(@Valid PD_WOCreateForm wocreateform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pd/PD_WOregi";
        }

        woservice.create(wocreateform.getOrderNum(), wocreateform.getDeliveryName(), wocreateform.getManager(),
                wocreateform.getDeliveryDate(), wocreateform.getProdCode(), wocreateform.getWOrder(),
                wocreateform.getMaking(), wocreateform.getFactory());
        return "redirect:/PD/wo";
    }

    // -------------------- PD_WorkHistory ----------------------------------//

    @GetMapping("/wh")
    public String WH_list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<PD_WorkHistory> paging = whservice.getList(page);
        model.addAttribute("paging", paging);
        return "pd/PD_WorkHistory";
    }

    // -------------------- PD_RS ----------------------------------//

    @GetMapping("/rs")
    public String RS_list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<PD_RS> paging = rsservice.getList(page);
        model.addAttribute("paging", paging);
        return "pd/PD_RS";
    }

    @GetMapping("/rs/pcinquiry")
    public String searchPurchaseInquiry(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<PC_PurchaseInquiry> paging = purchaseservice.searchPurchaseInquiry(page);
        model.addAttribute("paging", paging);
        return "PD/PD_PurchaseInquiry_list";
    }

    @GetMapping("/rs/pcinquiry/create")
    public String purchaseCreateUrl(Model model) {
        List<Mg_AccountMG_Entity> account = accountMg.accountManager();
        List<PD_Cost> costList = costservice.getList();

        model.addAttribute("account", account);
        model.addAttribute("costList", costList);
        return "PD/PD_PurchaseInquiry_create";
    }

    @PostMapping("/rs/pcinquiry/create")
    public String purchaseCreateform(
            @RequestParam(value = "itemName") String itemName,
            @RequestParam(value = "PurchaseDate") LocalDate PurchaseDate,
            @RequestParam(value = "totalCount") Double totalCount,
            @RequestParam(value = "warehouseName") String warehouseName) {

        String acceptance = "아니요";

        purchaseservice.purchaseSave(PurchaseDate, itemName, totalCount, warehouseName, acceptance);

        return "redirect:/PD/rs/pcinquiry";
    }

    // -------------------- PD_QC ----------------------------------//

    @GetMapping("/qc")
    public String QC_list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<PD_QC> paging = qcservice.getList(page);
        model.addAttribute("paging", paging);
        return "pd/PD_QC";
    }

    @GetMapping("/qc/regi")
    public String QC_regi(PD_QCCreateForm qccreateform) {
        return "pd/PD_QCregi";
    }

    @PostMapping("/qc/regi")
    public String QC_regi(@Valid PD_QCCreateForm qccreateform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pd/PD_QCregi";
        }

        qcservice.create(qccreateform.getId(), qccreateform.getQcTool(), qccreateform.
                        getProdCode(), qccreateform.getProdNum(), qccreateform.getQcNum(),
                qccreateform.getQcList(), qccreateform.getPF());
        return "redirect:/PD/qc";
    }
    // -------------------- PD_Cost ----------------------------------//

    @GetMapping("/cost")
    public String Cost_list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<PD_Cost> paging = costservice.getList(page);
        model.addAttribute("paging", paging);
        return "pd/PD_Cost";
    }

    @GetMapping("/cost/regi")
    public String Cost_regi(PD_CostCreateForm costcreateform) {
        return "pd/PD_Costregi";
    }

    @PostMapping("/cost/regi")
    public String Cost_regi(@Valid PD_CostCreateForm costcreateform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pd/PD_Costregi";
        }

        costservice.create(costcreateform.getPd_bom(), costcreateform.getProdName(),
                costcreateform.getKg(), costcreateform.getCost());
        return "redirect:/PD/cost";
    }
}