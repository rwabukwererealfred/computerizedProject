package com.studentRequest.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentRequest.model.Bankslip;
import com.studentRequest.model.Certificate;
import com.studentRequest.model.Internship;
import com.studentRequest.model.Certificate.CATEGORY;
import com.studentRequest.model.Recommendation;
import com.studentRequest.model.Recommendation.Status;
import com.studentRequest.model.Staff;
import com.studentRequest.model.StudentInfo;
import com.studentRequest.model.Transcript;
import com.studentRequest.model.User;
import com.studentRequest.response.ResponseMessage;
import com.studentRequest.services.SendRequestService;
import com.studentRequest.services.StaffRequestService;
import com.studentRequest.services.UserAccessService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "user/request/")
public class RequestsController {
	private Bankslip bankSlip;

	public RequestsController() {
		bankSlip = new Bankslip();
	}

	@Autowired
	private SendRequestService sendRequest;

	@Autowired
	private UserAccessService userAccessService;

	@Autowired
	private StaffRequestService staffService;

	@SuppressWarnings("rawtypes")
	@PostMapping("sendRecommendation")
	public ResponseEntity sendRecommandationRequest(@RequestBody HashMap<String, Object> map, Principal principal) {
		try {
			User username = userAccessService.findUsername(principal.getName())
					.orElseThrow(() -> new RuntimeException("username does not found"));
			Recommendation recommendation = new Recommendation();
			ObjectMapper mapper = new ObjectMapper();
			Staff staff = mapper.convertValue(map.get("staff"), Staff.class);
			String description = (String) map.get("description");
			recommendation.setDescription(description);
			recommendation.setStaff(staff);
			recommendation.setRequestDate(new Date());
			recommendation.setStudent(username.getStudent());
			recommendation.setStatus(Status.New);
			sendRequest.send(recommendation);
			return ResponseEntity.ok(new ResponseMessage("Recommandation request is successfull sent!!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("sendTranscript")
	public ResponseEntity sendTranscriptRequest(@RequestParam("file") MultipartFile file, Principal principal) {
		try {
			Transcript transcript = new Transcript();
			Bankslip slip = new Bankslip();
			User username = userAccessService.findUsername(principal.getName())
					.orElseThrow(() -> new RuntimeException("username does not found"));
			transcript.setStudent(username.getStudent());
			transcript.setRequestDate(new Date());
			transcript.setStatus(com.studentRequest.model.Document.Status.Applied);

			slip.setImage(file.getBytes());
			slip.setType(file.getContentType());
			slip.setName(file.getOriginalFilename());
			sendRequest.saveBankslip(slip);
			transcript.setBankslip(slip);
			sendRequest.sendTrancript(transcript);
			slip = new Bankslip();
			return ResponseEntity.ok(new ResponseMessage("transcript request is successfull sent!!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("sendCertificate/{status}")
	public ResponseEntity sendCertificateRequest(@PathVariable("status") String status,
			@RequestParam("file") MultipartFile file, Principal principal) {
		try {
			Certificate certificate = new Certificate();
			Bankslip slip = new Bankslip();
			User username = userAccessService.findUsername(principal.getName())
					.orElseThrow(() -> new RuntimeException("username does not found"));
			certificate.setStudent(username.getStudent());
			certificate.setRequestDate(new Date());
			certificate.setStatus(com.studentRequest.model.Document.Status.Applied);

			certificate.setCategory(CATEGORY.valueOf(status));
			slip.setImage(file.getBytes());
			slip.setType(file.getContentType());
			slip.setName(file.getOriginalFilename());
			sendRequest.saveBankslip(slip);
			certificate.setBankslip(slip);
			sendRequest.sendCertificate(certificate);
			slip = new Bankslip();
			return ResponseEntity.ok(new ResponseMessage("certificate request is successfull sent!!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/upload")

	public BodyBuilder uplaodImage(@RequestParam("bankSlipFile") MultipartFile imageFile) throws IOException {

		System.out.println("Original Image Byte Size - " + imageFile.getBytes().length);
		// ImageModel img = new ImageModel(file.getOriginalFilename(),
		// file.getContentType(),
		// compressZLib(file.getBytes()));
		this.bankSlip.setImage(imageFile.getBytes());
		this.bankSlip.setType(imageFile.getContentType());
		this.bankSlip.setName(imageFile.getOriginalFilename());

		return ResponseEntity.status(HttpStatus.OK);
	}

	@GetMapping("myRecommendationList/{usernames}")
	public List<Recommendation> findMyRecommendationList(@PathVariable("usernames") String usernames) {
		User username = userAccessService.findUsername(usernames)
				.orElseThrow(() -> new RuntimeException("username does not found"));

		return sendRequest.findByMyRequests(username.getStudent());
	}

	@GetMapping("/employees/download")
	public ResponseEntity<byte[]> downloadFile(Principal principal) throws Exception {
		User username = userAccessService.findUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("username does not found"));
		List<byte[]> employees = sendRequest.findByMyRequests(username.getStudent()).stream().map(i -> i.getImage())
				.collect(Collectors.toList());
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(employees);
		byte[] isr = json.getBytes();
		String fileName = "employees.json";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentLength(isr.length);
		respHeaders.setContentType(new MediaType("text", "json"));
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		System.out.print("out put is : " + fileName);
		return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
	}

	@GetMapping("/downloadFile/{id}/{status}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("id") int id, @PathVariable("status") String status) {

		String fileName = "";
		String fileType = "";
		byte[] image = new byte[1024];

		if (status.equals("recommendation")) {
			Recommendation recoId = sendRequest.findById(id);
			image = recoId.getImage();
			fileName = recoId.getFileName();
			fileType = recoId.getFileTye();

		} else if (status.equals("transcript")) {
			Transcript recoId = staffService.findByTrascriptId(id);
			image = recoId.getImage();
			fileName = recoId.getFileName();
			fileType = recoId.getFileTye();
		} else if (status.equals("certificate")) {
			Certificate recoId = staffService.findByCertificateId(id);
			image = recoId.getImage();
			fileName = recoId.getFileName();
			fileType = recoId.getFileTye();
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType(fileType)).body(new ByteArrayResource(image));
	}

	@GetMapping("downloadBankslip/{id}")
	public ResponseEntity<Resource> downloadBankslip(@PathVariable("id") int id) {

		Bankslip bankslip = staffService.findByBankslipId(id);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + bankslip.getName())
				.contentType(MediaType.parseMediaType(bankslip.getType()))
				.body(new ByteArrayResource(bankslip.getImage()));
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "validateStudentInfo/{id}")
	public ResponseEntity validateStudentInfo(@PathVariable("id") String id) {
		StudentInfo info = sendRequest.stInfoId(id);
		if (info.getCredit() >= 100) {
			if (info.getAverageMark() >= 12) {
				return ResponseEntity.ok().body(new ResponseMessage("ok"));
			} else {
				return ResponseEntity.badRequest().body(new ResponseMessage("Average Marks must not be under 12"));
			}
		} else {
			return ResponseEntity.badRequest().body(new ResponseMessage("Credit must not be under 100"));
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "sendInternship")
	public ResponseEntity sendInternship(@RequestBody Internship internship, Principal principal) {
		User username = userAccessService.findUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("username does not found"));
		StudentInfo st = sendRequest.stInfoId(username.getStudent().getId());
		if (st.getCredit() >= 60) {
			internship.setStudent(username.getStudent());
			internship.setRequestDate(new Date());
			internship.setStatus(com.studentRequest.model.Letter.Status.Applied);
			sendRequest.sendInternshipRequest(internship);
			return ResponseEntity.ok().body(new ResponseMessage("well successfull sent"));
		} else {
			return ResponseEntity.badRequest().body(new ResponseMessage("you must be having above 60 credit"));
		}
	}

}
