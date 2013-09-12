class PortectedJava{
	public static void main(String[] args)
	{
		Vehicle vehicle = new Vehicle();
		Car car = new Car();
		car.tow(car);
		car.tow(vehicle);
	}
}
class Vehicle {
	// final void checkEngine(){}//组合1，final不能override
	protected void checkEngine(){}
}

class Car extends Vehicle {
	void start() { checkEngine(); /*OK*/}
	// void checkEngine(){}//组合1
	void tow(Car car) {
		car.checkEngine(); /*OK*/
	}
	void tow(Vehicle vehicle) {
		vehicle.checkEngine(); //OK
	}
}

class GasStation {
	void fileeGas(Vehicle vehicle){
		vehicle.checkEngine(); //OK
	}
}