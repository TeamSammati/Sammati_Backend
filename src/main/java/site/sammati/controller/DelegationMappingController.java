package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.entity.DelegationMapping;
import site.sammati.service.DelegationMappingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class DelegationMappingController
{
    private final DelegationMappingService delegationMappingService;
    @PostMapping("/add-delegation-mapping")
    public Integer add_delegation_mapping(@RequestBody DelegationMapping delegationMapping) {
        return delegationMappingService.saveDelegationMapping(delegationMapping);
    }
}
