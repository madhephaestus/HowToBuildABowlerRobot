import javafx.scene.transform.Affine;
return new ICadGenerator(){
	public ArrayList<CSG> generateCad(DHParameterKinematics d, int linkIndex) {
		ArrayList<DHLink> dhLinks = d.getChain().getLinks()
		ArrayList<CSG> allCad=new ArrayList<>()
		int i=linkIndex;
		DHLink dh = dhLinks.get(linkIndex)
		// Hardware to engineering units configuration
		LinkConfiguration conf = d.getLinkConfiguration(i);
		// Engineering units to kinematics link (limits and hardware type abstraction)
		AbstractLink abstractLink = d.getAbstractLink(i);
		// Transform used by the UI to render the location of the object
		Affine manipulator = dh.getListener();
		// loading the vitamins referenced in the configuration
		CSG servo=   Vitamins.get(conf.getElectroMechanicalType(),conf.getElectroMechanicalSize())
		
		CSG tmpSrv = moveDHValues(servo,dh)

		//Compute the location of the base of this limb to place objects at the root of the limb
		TransformNR step = d.getRobotToFiducialTransform()
		Transform locationOfBaseOfLimb = com.neuronrobotics.bowlerstudio.physics.TransformFactory.nrToCSG(step)
		
		
		tmpSrv.setManipulator(manipulator)
		allCad.add(tmpSrv)
		println "Generating link: "+linkIndex

		if(i==0){
			// more at https://github.com/NeuronRobotics/java-bowler/blob/development/src/main/java/com/neuronrobotics/sdk/addons/kinematics/DHLink.java
			println dh
			println "D = "+dh.getD()// this is the height of the link
			println "R = "+dh.getR()// this is the radius of rotation of the link
			println "Alpha = "+Math.toDegrees(dh.getAlpha())// this is the alpha rotation
			println "Theta = "+Math.toDegrees(dh.getTheta())// this is the rotation about hte final like orentation
			println conf // gets the link hardware map from https://github.com/NeuronRobotics/java-bowler/blob/development/src/main/java/com/neuronrobotics/sdk/addons/kinematics/LinkConfiguration.java
			println conf.getHardwareIndex() // gets the link hardware index
			println conf.getScale() // gets the link hardware scale to degrees from link units
			// more from https://github.com/NeuronRobotics/java-bowler/blob/development/src/main/java/com/neuronrobotics/sdk/addons/kinematics/AbstractLink.java
			println  "Max engineering units for link = " + abstractLink.getMaxEngineeringUnits() 
			println  "Min engineering units for link = " + abstractLink.getMinEngineeringUnits() 
			println "Position "+abstractLink.getCurrentEngineeringUnits()
			println manipulator
		}
		return allCad;
	}
	public ArrayList<CSG> generateBody(MobileBase b ) {
		CSG body  = new Cube(40).toCSG()
		body.setManipulator(b.getRootListener());
		
		return [body];
	}
};