package com.foxboro.entity;

import java.util.List;

public class Menu {
	private Function moduleMenu;	//模块菜单
	private Function mainMenu;	//主菜单
	private List<Function> subMenu;		//分菜单
	
	
	public Function getModuleMenu() {
		return moduleMenu;
	}
	public void setModuleMenu(Function moduleMenu) {
		this.moduleMenu = moduleMenu;
	}
	
	public Function getMainMenu() {
		return mainMenu;
	}
	public void setMainMenu(Function mainMenu) {
		this.mainMenu = mainMenu;
	}
	public List<Function> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Function> subMenu) {
		this.subMenu = subMenu;
	}
	
}
