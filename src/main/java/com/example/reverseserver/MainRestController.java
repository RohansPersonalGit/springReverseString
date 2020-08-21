package com.example.reverseserver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/reverse")
public class  MainRestController {

    /**
     * Just a base url function to provide some inutition to the user on what to use
     * @return
     */
    @GetMapping(value = "/", produces = "text/plain")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok().body("Hi you can use me to reverse string just add a string of up to 2000 characters after the /! i.e /hello");
    }

    /**
     *
     * @param word provided as a component of url path i.e /hello would return ->olleh
     *             has a limit of up too 2000 characters in the string
     * @return a Text/plain representation of the provided string
     */
    @GetMapping(value = "/{word}", produces = "text/plain")
    public ResponseEntity<String> getReverse(@PathVariable(value="word") String word){
        if(word.length()>2000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("I have a character limit of 2000, your request was too long!");        }
        String reversed = new StringBuilder(word).reverse().toString();
        return  ResponseEntity.ok().body(reversed);
    }

}
