package com.socialnetwork;

import com.socialnetwork.handler.SocialNetworkHelper;
import com.socialnetwork.model.SocialNetwork;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class SocialNetworkHelperTest {

	@Test
	public  void findShortestDistance_Scenario1() {
		SocialNetworkHelper helper = new SocialNetworkHelper();
		SocialNetwork friendship1 = new SocialNetwork();
		friendship1.setUserId1("John");
		friendship1.setUserId2("Teressa");
		SocialNetwork friendship2 = new SocialNetwork();
		friendship2.setUserId1("Teressa");
		friendship2.setUserId2("Sue");
		SocialNetwork friendship3 = new SocialNetwork();
		friendship3.setUserId1("John");
		friendship3.setUserId2("Rob");

		List<SocialNetwork> connections = new ArrayList<>();
		connections.add(friendship1);
		connections.add(friendship2);
		connections.add(friendship3);
		Assert.isTrue(helper.findShortestPath(connections,"Sue","John")==2);
	}
	@Test
	public  void findShortestDistance_Scenario2() {
		SocialNetworkHelper helper = new SocialNetworkHelper();
		SocialNetwork friendship1 = new SocialNetwork();
		friendship1.setUserId1("John");
		friendship1.setUserId2("Teressa");
		List<SocialNetwork> connections = new ArrayList<>();
		connections.add(friendship1);
		Assert.isTrue(helper.findShortestPath(connections,"Sue","Nick")==-1);
	}
	@Test
	public  void findShortestDistance_Scenario3() {
		SocialNetworkHelper helper = new SocialNetworkHelper();
		SocialNetwork friendship1 = new SocialNetwork();
		friendship1.setUserId1("John");
		friendship1.setUserId2("Teressa");
		List<SocialNetwork> connections = new ArrayList<>();
		connections.add(friendship1);
		Assert.isTrue(helper.findShortestPath(connections,"John","Teressa")==1);
	}
	@Test
	public void findConnectionsTest_Scenario1(){
		SocialNetworkHelper helper = new SocialNetworkHelper();
		SocialNetwork friendship1 = new SocialNetwork();
		friendship1.setUserId1("John");
		friendship1.setUserId2("Teressa");
		SocialNetwork friendship2 = new SocialNetwork();
		friendship2.setUserId1("Teressa");
		friendship2.setUserId2("Sue");
		SocialNetwork friendship3 = new SocialNetwork();
		friendship3.setUserId1("John");
		friendship3.setUserId2("Rob");

		List<SocialNetwork> connections = new ArrayList<>();
		connections.add(friendship1);
		connections.add(friendship2);
		connections.add(friendship3);
		Assert.isTrue(helper.findConnections(connections).size()==2);
	}
	@Test
	public void findConnectionsTest_Scenario2(){
		SocialNetworkHelper helper = new SocialNetworkHelper();
		SocialNetwork friendship1 = new SocialNetwork();
		friendship1.setUserId1("John");
		friendship1.setUserId2("Teressa");
		SocialNetwork friendship2 = new SocialNetwork();
		friendship2.setUserId1("Teressa");
		friendship2.setUserId2("Sue");
		SocialNetwork friendship3 = new SocialNetwork();
		friendship3.setUserId1("Teressa");
		friendship3.setUserId2("Rob");

		List<SocialNetwork> connections = new ArrayList<>();
		connections.add(friendship1);
		connections.add(friendship2);
		connections.add(friendship3);
		Assert.isTrue(helper.findConnections(connections).size()==1);
	}
	@Test
	public void findConnectionsTest_Scenario3(){
		SocialNetworkHelper helper = new SocialNetworkHelper();
		List<SocialNetwork> connections = new ArrayList<>();
	    Assert.isTrue(helper.findConnections(connections).size()==0);
	}
	@Test
	public void findDegreeCertainiy_scenario1(){
		SocialNetworkHelper helper = new SocialNetworkHelper();
		SocialNetwork friendship1 = new SocialNetwork();
		friendship1.setUserId1("John");
		friendship1.setUserId2("Teressa");
		SocialNetwork friendship2 = new SocialNetwork();
		friendship2.setUserId1("Teressa");
		friendship2.setUserId2("Sue");
		SocialNetwork friendship3 = new SocialNetwork();
		friendship3.setUserId1("Teressa");
		friendship3.setUserId2("Rob");
		List<SocialNetwork> connections = new ArrayList<>();
		connections.add(friendship1);
		connections.add(friendship2);
		connections.add(friendship3);
		Assert.isTrue(helper.calculateDegreeCentrality(connections,"Teressa")==3);
	}
	@Test
	public void findDegreeCertainiy_scenario2(){
		SocialNetworkHelper helper = new SocialNetworkHelper();
		SocialNetwork friendship1 = new SocialNetwork();
		friendship1.setUserId1("John");
		friendship1.setUserId2("Teressa");
		SocialNetwork friendship2 = new SocialNetwork();
		friendship2.setUserId1("Teressa");
		friendship2.setUserId2("Sue");
		SocialNetwork friendship3 = new SocialNetwork();
		friendship3.setUserId1("Teressa");
		friendship3.setUserId2("Rob");
		List<SocialNetwork> connections = new ArrayList<>();
		connections.add(friendship1);
		connections.add(friendship2);
		connections.add(friendship3);
		Assert.isTrue(helper.calculateDegreeCentrality(connections,"Rob")==1);
	}

}
