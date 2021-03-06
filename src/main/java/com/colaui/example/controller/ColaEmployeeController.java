package com.colaui.example.controller;

import com.colaui.example.model.ColaEmployee;
import com.colaui.example.service.ColaEmployeeService;
import com.colaui.helper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class ColaEmployeeController {
	@Autowired
	private ColaEmployeeService employeeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Page<ColaEmployee> paging(@RequestParam int pageSize,
			@RequestParam int pageNo, @RequestParam(required=false) String contain) {
		return employeeService.getPage(pageSize, pageNo, contain);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void save(@RequestBody ColaEmployee employee) {
		employeeService.save(employee);
	}

	@RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long id) {
		employeeService.delete(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	public void update(@RequestBody ColaEmployee employee) {
		employeeService.update(employee);
	}

	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	public ColaEmployee find(@PathVariable("id") long id) {
		return employeeService.find(id);
	}

	@RequestMapping(value = "/{from}/{limit}", method = RequestMethod.GET)
	public List<ColaEmployee> find(@PathVariable("from") int from,
			@PathVariable("limit") int limit) {
		return employeeService.find(from, limit);
	}
}
