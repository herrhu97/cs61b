public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(
            Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2)
            );
    }

    public double calcForceExertedBy(Body b) {
        if (this.equals(b)) {
            return 0.0;
        }
        double distance = this.calcDistance(b);
        // System.out.println(2.872 / (this.mass * b.mass / Math.pow(distance, 2)));
        return  6.67e-11 * this.mass * b.mass / Math.pow(distance, 2); 
    }

    public double calcNetForceExertedByX(Body[] bodys) {
        double sumX = 0.0;

        for (Body b: bodys) {
            if (this.equals(b)) {
                continue;
            }
            double force = this.calcForceExertedBy(b);
            sumX += (b.xxPos - this.xxPos) / this.calcDistance(b) * force;
        }
        return sumX;
    }

    public double calcNetForceExertedByY(Body[] bodys) {
        double sumY = 0.0;

        for (Body b : bodys) {
            if (this.equals(b)) {
                continue;
            }
            double force = this.calcForceExertedBy(b);
            sumY += (b.yyPos - this.yyPos) / this.calcDistance(b) * force;
        }
        return sumY;
    }

    public double calcForceExertedByY(Body b) {
        return this.calcNetForceExertedByY(new Body[] { b });
    } 


    public double calcForceExertedByX(Body b) {
        return this.calcNetForceExertedByX(new Body[]{b});
    }

    public void update(double duration, double forceX, double forceY) {
        double aX = forceX / this.mass;
        double aY = forceY / this.mass;

        this.xxVel += duration * aX;
        this.yyVel += duration * aY;

        this.xxPos += this.xxVel * duration;
        this.yyPos += this.yyVel * duration;
    }

    /**
     * Draw the picture of the Body according to its position
     */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
