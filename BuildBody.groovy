import javafx.scene.transform.Affine;
return new ICadGenerator(){
	public ArrayList<CSG> generateCad(DHParameterKinematics d, int linkIndex) {
		ArrayList<DHLink> dhLinks = d.getChain().getLinks()
		// D-H Parameters engine
		DHLink dh = dhLinks.get(linkIndex)
		// Transform used by the UI to render the location of the object
		Affine manipulator = dh.getListener();
		CSG legPart  = new Cube(20).toCSG()
		legPart.setColor(javafx.scene.paint.Color.RED);
		return [legPart].collect{
			it.setManipulator(manipulator)
		}
	}
	public ArrayList<CSG> generateBody(MobileBase b ) {
		CSG body  = new Cube(20).toCSG()
		body.setColor(javafx.scene.paint.Color.BLUE);	
		return [body].collect{
			it.setManipulator(b.getRootListener());
		}
	}
};