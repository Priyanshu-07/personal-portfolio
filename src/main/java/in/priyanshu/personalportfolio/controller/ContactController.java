package in.priyanshu.personalportfolio.controller;

import in.priyanshu.personalportfolio.pojo.ContactForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Value("${web3forms.access-key}")
    private String accessKey;

    private final RestTemplate rest = new RestTemplate();

    @PostMapping
    public ResponseEntity<String> submit(@RequestBody ContactForm form) {
        String url = "https://api.web3forms.com/submit";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("access_key", accessKey);
        body.add("name", form.getName());
        body.add("email", form.getEmail());
        body.add("message", form.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(body, headers);
        ResponseEntity<String> resp = rest.postForEntity(url, req, String.class);

        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
}
