import java.awt.*;

public class NBody{
	public static double readRadius(String path){
		In in = new In(path);
		int N = in.readInt();
		double r = in.readDouble();
		return r;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int N = in.readInt();
		double r = in.readDouble();
		Planet[] planets = new Planet[N];
		for(int i = 0;i<N;i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP,yP,xV,yV,m,img);
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		//Initial drawing dection
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-r,r);
		StdDraw.picture(0,0,"./images/starfield.jpg");

		for(Planet p:planets){
			p.draw();
		}
		//animation section
		double time = 0;
		while(time!=T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for(int i = 0;i<planets.length;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);				
			}
			for(int i = 0;i<planets.length;i++){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,"./images/starfield.jpg");
			for(Planet p:planets){
				p.draw();
			}
			StdDraw.show(10);
			time+=dt;
		}

		StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
	}
}