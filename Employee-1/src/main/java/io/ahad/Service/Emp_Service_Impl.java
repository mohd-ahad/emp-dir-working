package io.ahad.Service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import io.ahad.Entity.Employee;
import io.ahad.Repository.Emp_Direct_Repo;
import java.lang.*;


@Service
public class Emp_Service_Impl implements Emp_Service {
	@Autowired
	 private Emp_Direct_Repo repository;

	@Autowired
    private ModelMapper mapper;

	
	@Override
	public List<Employee> getAll() {
		return repository.findAll();
	}

	@Override
	public EmployeeDTO findByName(String name) {
		Employee emp=repository.findByName(name).orElseThrow(
                () -> new NotFoundException("Name", "name", name));
        return mapToDto(emp);
		//int compareValue = repository.findByName(name).compareToIgnoreCase(name);
	}
	@Override
	public List<Employee> findAllByName(String name)throws NotFoundException {
	List<Employee> emp=repository.findAll(name);
			if(CollectionUtils.isEmpty(emp)){
           throw new NotFoundException("Name", "name", name);
			}
	return emp;
	
	}
	
	 private EmployeeDTO mapToDto(Employee employee) {
		 EmployeeDTO postDto = mapper.map(employee, EmployeeDTO.class);
	        return postDto;
	    }

	    // convert DTO to entity
	    private Employee mapToEntity(EmployeeDTO employeeDTO) {
	        Employee employee = mapper.map(employeeDTO, Employee.class);
	        return employee;
	    }
		
//		boolean firstNameComp=name.equalsIgnoreCase
//		(repository.findByName(name).toString().substring(0,pos));
//		
//		int pos=0;
//		for(int i=0;i<name.length();i++) {
//			char ch=name.charAt(i);
//			if(ch== ' ' ) {
//				pos=i;
//				break;
//			}
//		}		 
//		

	
	
//	@Override
//	public Employee findById(int id, Pageable pageable) {
//		 Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//	}
	
	 public Page<Employee> findEmployeeWithPagination(int offset,int pageSize){
	        Page<Employee> employee = repository.findAll(PageRequest.of(offset, pageSize));
	        return  employee;
	    }

	

	

//	@Override
//	public List<Employee> findByAccountName(String accountName) {
//		
//		return repository.findAl(accountName);
//	}

//	@Override
//	public List<Employee> findAll(String keyword) {
//		if(keyword!=null) {
//		return repository.findAll(keyword);
//	}
//		return repository.findAll();
//	}
	
	 
//	 public Page<Employee> findEmployeeWithPagination(String name,int offset,int pageSize){
//	        Page<Employee> employee = repository.findEmployeeByPagination(name,PageRequest.of(offset, pageSize));
//	        return  employee;
//	    }

}