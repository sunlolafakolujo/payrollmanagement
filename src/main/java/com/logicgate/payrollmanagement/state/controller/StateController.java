package com.logicgate.payrollmanagement.state.controller;

import com.logicgate.payrollmanagement.state.model.EditState;
import com.logicgate.payrollmanagement.state.model.PostState;
import com.logicgate.payrollmanagement.state.model.Province;
import com.logicgate.payrollmanagement.state.model.StateDto;
import com.logicgate.payrollmanagement.state.service.StateService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/payroll")
public record StateController(StateService stateService,ModelMapper modelMapper) {

    @PostMapping("/addState")
    public ResponseEntity<PostState> addState(@RequestBody PostState postState) {
        Province province = modelMapper.map(postState, Province.class);
        Province post = stateService.addState(province);
        PostState posted = modelMapper.map(post, PostState.class);
        return new ResponseEntity<>(posted, HttpStatus.CREATED);
    }

    @GetMapping("/findState")
    public ResponseEntity<StateDto> getStateByName(@RequestParam("stateName") String stateName) {
        return new ResponseEntity<>(convertStateToDto(stateService.fetchStateByName(stateName)), HttpStatus.OK);
    }

    @GetMapping("/allStates")
    public ResponseEntity<List<StateDto>> getAllStates(@RequestParam("pageNumber") int pageNumber,
                                                       @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(stateService.fetchAllStates(pageNumber, pageSize)
                .stream()
                .map(this::convertStateToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editProvinceById")
    public ResponseEntity<EditState> updateState(@RequestBody EditState editState,
                                                 @RequestParam("id") Long id) {
        Province province = modelMapper.map(editState, Province.class);
        Province post = stateService.editState(province, id);
        EditState posted = modelMapper.map(post, EditState.class);
        return new ResponseEntity<>(posted, HttpStatus.OK);
    }

    @DeleteMapping("/state")
    public ResponseEntity<?> deleteState(@RequestParam("id") Long id) {
        stateService.deleteState(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/allStates")
    public ResponseEntity<?> deleteAllStates() {
        stateService.deleteAllStates();
        return ResponseEntity.noContent().build();
    }


    private StateDto convertStateToDto(Province province) {
        StateDto stateDto = new StateDto();
        stateDto.setId(province.getId());
        stateDto.setStateName(province.getStateName());
        return stateDto;
    }
}
