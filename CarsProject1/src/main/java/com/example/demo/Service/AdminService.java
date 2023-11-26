package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;

@Service
public interface AdminService {
	
	boolean findAdmin(Admin admin);

}
