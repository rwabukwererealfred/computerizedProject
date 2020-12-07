package com.studentRequest.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentRequest.model.Bankslip;
import com.studentRequest.model.Certificate;
import com.studentRequest.model.Internship;
import com.studentRequest.model.Recommendation;
import com.studentRequest.model.Recommendation.Status;
import com.studentRequest.model.Transcript;
import com.studentRequest.model.User;
import com.studentRequest.response.ResponseMessage;
import com.studentRequest.services.SendRequestService;
import com.studentRequest.services.StaffRequestService;
import com.studentRequest.services.UserAccessService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "staff/request/")
public class StaffRequestController {

	private Recommendation recommendation = new Recommendation();

	@Autowired
	private StaffRequestService staffRequestService;

	@Autowired
	private UserAccessService userAccessService;

	@Autowired
	private SendRequestService sendService;

	@GetMapping("myRecommendationRequest")
	public List<Recommendation> recoList(Principal principal) {
		User username = userAccessService.findUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("username does not found"));
		return staffRequestService.findStaffRecommendation(username.getStaff()).stream().filter(i->i.getStatus()== Status.New)
				.collect(Collectors.toList());
	}
	@GetMapping("appovedRecommendationRequest")
	public List<Recommendation> approvedRecoList(Principal principal) {
		User username = userAccessService.findUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("username does not found"));
		return staffRequestService.findStaffRecommendation(username.getStaff()).stream().filter(i->i.getStatus()== Status.Approved)
				.collect(Collectors.toList());
	}
	

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "approveRequest/{id}")
	public ResponseEntity approveImage(@PathVariable("id") int id) {
		Recommendation rec = staffRequestService.findById(id);
		rec.setFileName(this.recommendation.getFileName());
		rec.setFileTye(this.recommendation.getFileTye());
		rec.setImage(this.recommendation.getImage());
		rec.setStatus(Status.Approved);
		sendService.send(rec);
		return ResponseEntity.ok().body(new ResponseMessage("well approved"));
	}

	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {

		System.out.println("Original Image Byte Size - " + imageFile.getBytes().length);
		System.out.print("file name: "+imageFile.getContentType()+" "+imageFile.getOriginalFilename());
		// ImageModel img = new ImageModel(file.getOriginalFilename(),
		// file.getContentType(),
		// compressZLib(file.getBytes()));
		this.recommendation.setImage(compressZLib(imageFile.getBytes()));
		this.recommendation.setFileTye(imageFile.getContentType());
		this.recommendation.setFileName(imageFile.getOriginalFilename());

		System.out.print("file name: "+this.recommendation.getFileName());
		return ResponseEntity.status(HttpStatus.OK);
	}

	public static byte[] compressZLib(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	
	@GetMapping(value="transcript/{status}")
	public List<Transcript>transcriptList(@PathVariable("status")String status){
		
		return staffRequestService.findTrascriptList().stream().filter(i->i.getStatus().toString().equals(status))
				.collect(Collectors.toList());
	}
	@GetMapping(value="transcript")
	public List<Transcript>studentTranscriptList(Principal principal){
		User username = userAccessService.findUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("username does not found"));
		return staffRequestService.findTrascriptList().stream().filter(i->i.getStudent().getId() == username.getStudent().getId()).collect(Collectors.toList());
	}
	@GetMapping(value="internship")
	public List<Internship>studentInternshipList(Principal principal){
		User username = userAccessService.findUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("username does not found"));
		return staffRequestService.internshipList().stream().filter(i->i.getStudent().getId() == username.getStudent().getId()).collect(Collectors.toList());
	}
	@GetMapping(value="certificate")
	public List<Certificate>studentCertificateList(Principal principal){
		User username = userAccessService.findUsername(principal.getName())
				.orElseThrow(() -> new RuntimeException("username does not found"));
		return staffRequestService.certificateList().stream().filter(i->i.getStudent().getId() == username.getStudent().getId()).collect(Collectors.toList());
	}
	@GetMapping(value="certificate/{status}")
	public List<Certificate>certificateList(@PathVariable("status")String status){
		
		return staffRequestService.certificateList().stream().filter(i->i.getStatus().toString().equals(status))
				.collect(Collectors.toList());
	}
	@SuppressWarnings("rawtypes")
	@PutMapping(value="approveTranscript/{id}")
	public ResponseEntity approveTransript(@PathVariable("id")int id, @RequestBody HashMap<String, Object>map) {
		ObjectMapper  mapper = new ObjectMapper();
		Transcript transcript = mapper.convertValue(map.get("transcript"), Transcript.class);
		String bankslipNumber =(String) map.get("number");
		Optional<Bankslip>findByNumber = staffRequestService.bankSlipList().stream().filter(i->i.getBanksleepNumber().equals(bankslipNumber))
				.findAny();
		if(findByNumber.isPresent()) {
			return ResponseEntity.badRequest().body(new ResponseMessage("This bankSlip number is arleady used!!"));
		}else {
		Transcript tr = staffRequestService.findByTrascriptId(id);
		tr.setStatus(com.studentRequest.model.Document.Status.Pending);
		tr.setAmount(transcript.getAmount());
		
		Bankslip bk = staffRequestService.findByBankslipId(tr.getBankslip().getId());
		bk.setAmount(transcript.getAmount());
		bk.setBanksleepNumber(bankslipNumber);
		
		sendService.saveBankslip(bk);
		sendService.sendTrancript(tr);
		
		return ResponseEntity.ok(new ResponseMessage("transcript request is successfull Appoved!!"));
		}
	}
	@SuppressWarnings("rawtypes")
	@PutMapping(value="rejectTranscript/{id}")
	public ResponseEntity rejectTransript(@PathVariable("id")int id, @RequestBody Transcript transcript) {
		System.out.println("comment: "+transcript.getCommennt());
		Transcript tr = staffRequestService.findByTrascriptId(id);
		
		tr.setStatus(com.studentRequest.model.Document.Status.Rejected);
		tr.setCommennt(transcript.getCommennt());
		sendService.sendTrancript(tr);
		
		return ResponseEntity.ok(new ResponseMessage("transcript request is successfull Rejected!!"));
	}
	@SuppressWarnings("rawtypes")
	@PutMapping(value="approveCertificate/{id}")
	public ResponseEntity approveCertificate(@PathVariable("id")int id, @RequestBody HashMap<String, Object>map) {
		ObjectMapper  mapper = new ObjectMapper();
		Certificate certificate = mapper.convertValue(map.get("certificate"), Certificate.class);
		String bankslipNumber =(String) map.get("number");
		
		Certificate tr = staffRequestService.findByCertificateId(id);
		tr.setStatus(com.studentRequest.model.Document.Status.Pending);
		tr.setAmount(certificate.getAmount());
		
		Bankslip bk = staffRequestService.findByBankslipId(tr.getBankslip().getId());
		bk.setAmount(certificate.getAmount());
		bk.setBanksleepNumber(bankslipNumber);
		
		sendService.saveBankslip(bk);
		sendService.sendCertificate(tr);
		
		return ResponseEntity.ok(new ResponseMessage("Certificate request is successfull Appoved!!"));
		
	}
	@SuppressWarnings("rawtypes")
	@PutMapping(value="rejectCertificate/{id}")
	public ResponseEntity rejectCertificate(@PathVariable("id")int id, @RequestBody Certificate certificate) {
		System.out.println("comment: "+certificate.getCommennt());
		Certificate tr = staffRequestService.findByCertificateId(id);
		
		tr.setStatus(com.studentRequest.model.Document.Status.Rejected);
		tr.setCommennt(certificate.getCommennt());
		sendService.sendCertificate(tr);
		
		return ResponseEntity.ok(new ResponseMessage("certificate request is successfull Rejected!!"));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("attachTranscriptFile/{id}")
	public ResponseEntity sendTranscriptRequest(@PathVariable("id")int id,@RequestParam("file") MultipartFile file) {
		try {
		
			Transcript transcript = staffRequestService.findByTrascriptId(id);
			System.out.println("trans: "+transcript.getId());
			transcript.setFileName(file.getOriginalFilename());
			transcript.setImage(file.getBytes());
			transcript.setFileTye(file.getContentType());
			transcript.setStatus(com.studentRequest.model.Document.Status.Approved);
			sendService.sendTrancript(transcript);
			
			return ResponseEntity.ok(new ResponseMessage("transcript request is successfull approved!!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@SuppressWarnings("rawtypes")
	@PutMapping("attachCertificateFile/{id}")
	public ResponseEntity sendCertificateRequest(@PathVariable("id")int id,@RequestParam("file") MultipartFile file) {
		try {
		
			Certificate certificate = staffRequestService.findByCertificateId(id);
			System.out.println("trans: "+certificate.getId());
			certificate.setFileName(file.getOriginalFilename());
			certificate.setImage(file.getBytes());
			certificate.setFileTye(file.getContentType());
			certificate.setStatus(com.studentRequest.model.Document.Status.Approved);
			sendService.sendCertificate(certificate);
			
			return ResponseEntity.ok(new ResponseMessage("certificate request is successfull approved!!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	public Recommendation getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(Recommendation recommendation) {
		this.recommendation = recommendation;
	}
	
	
	
	
}
