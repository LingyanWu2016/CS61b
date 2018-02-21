import java.awt.*;
public class Planet{
	//instance variables
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;


	static final double G = 6.67 * Math.pow(10,-11);
	//constructors
	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public void draw(){
		String filename = "./images/"+this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,filename);
	}

	public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double rSquared = (dx * dx) + (dy * dy);
        return Math.sqrt(rSquared);
    }

    public double calcForceExertedBy(Planet p){
    	
    	double r = calcDistance(p);
    	double F =  G * this.mass * p.mass/(r*r);
    	return F;
    }

    public double calcForceExertedByX(Planet p){
    	double dx = p.xxPos - this.xxPos;
    	double r = calcDistance(p);
    	double Fx = calcForceExertedBy(p) * dx/r;
    	return Fx;
    }

    public double calcForceExertedByY(Planet p){
    	double dy = p.yyPos - this.yyPos;
    	double r = calcDistance(p);
    	double Fy = calcForceExertedBy(p) * dy/r;
    	return Fy;
    }

    public double calcNetForceExertedByX(Planet[] planets){
    	double netForceX = 0;
    	for(Planet p:planets){
    		if(p==this) continue;
    		else{
    			netForceX += calcForceExertedByX(p);
    		}
    	}
    	return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets){
    	double netForceY = 0;
    	for(Planet p:planets){
    		if(p==this) continue;
    		else{
    			netForceY += calcForceExertedByY(p);
    		}
    	}
    	return netForceY;
    }

    public void update(double dt,double fX,double fY){
    	double ax = fX/this.mass;
    	double ay = fY/this.mass;
    	this.xxVel += dt*ax;
    	this.yyVel += dt*ay;
    	this.xxPos += dt*this.xxVel;
    	this.yyPos += dt*this.yyVel;
    }
}