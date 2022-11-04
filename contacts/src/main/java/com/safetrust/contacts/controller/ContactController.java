package com.safetrust.contacts.controller;

import com.safetrust.contacts.service.ContactService;
import com.safetrust.contacts.viewmodel.ContactPostVm;
import com.safetrust.contacts.viewmodel.ContactVm;
import com.safetrust.contacts.viewmodel.ErrorVm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {
    private final ContactService contactService;

    public ContactController (ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    @Operation(summary = "Get list contact")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<List<ContactVm>> getContact() {
        return ResponseEntity.ok(contactService.getContact());
    }

    @GetMapping("/contact/{id}")
    @Operation(summary = "Find contact by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<ContactVm> getContact(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @PostMapping("/contact")
    @Operation(summary = "Create new contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ContactVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<ContactVm> createContact(@Valid @RequestBody ContactPostVm contactPostVm, UriComponentsBuilder uriComponentsBuilder) {
        ContactVm contactVm = contactService.createContact(contactPostVm);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/contact/{id}")
                .buildAndExpand(contactVm.id()).toUri())
                .body(contactVm);
    }

    @PutMapping("/contact/{id}")
    @Operation(summary = "Update a contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<ContactVm> updateContact(@Valid @RequestBody ContactPostVm contactPostVm, @PathVariable long id) {
        return ResponseEntity.ok(contactService.updateContact(contactPostVm, id));
    }

    @DeleteMapping("/contact/{id}")
    @Operation(summary = "Delete a contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<Void> deleteContact(@PathVariable long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
