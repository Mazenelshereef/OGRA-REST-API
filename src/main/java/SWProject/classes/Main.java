package SWProject.classes;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
        int mainMenuChoice = 0;
        while(mainMenuChoice != 3)
        {
            System.out.println("Welcome to Ogra app");
            System.out.println("1- Register new account");
            System.out.println("2- login to your account");
            System.out.println("3- Exit");
            mainMenuChoice = input.nextInt();
            switch(mainMenuChoice)
            {
                //Main Menu -> Register new account
                case 1:
                {
                    System.out.println("1- Passenger account");
                    System.out.println("2- Driver account");
                    System.out.println("3- Go back to Main Menu");
                    int accountChoice = input.nextInt();
                    switch(accountChoice)
                    {
                        //Register -> Passenger account
                        case 1:
                        {
                            PassengerInfo info = new PassengerInfo();
                            System.out.println("Enter your username:");
                            info.setUsername(input.next());
                            System.out.println("Enter your e-mail(optional: enter 0 if you don't ant to):");
							String mail = input.next();
							if (!mail.equals("0"))
                            	info.setEmail(mail);
                            System.out.println("Enter password for account:");
                            info.setPassword(input.next());
                            System.out.println("Enter your phone number:");
                            info.setMobileNumber(input.next());
                            //register the account to the system
							try {
								if (PassengerAuthenticator.getInstance().register(info)) {
									System.out.println("Account registered successfully.");
                          	  		System.out.println("-----------------------------------");
								} else {
									System.out.println("Something went wrong!. Please try again.");
								}
							} catch (Exception e) {
								System.out.println(e.toString());
							}
                            break;
                        }
                        //Register -> Driver account
                        case 2:
                        {
                            DriverInfo info = new DriverInfo();
                            System.out.println("Enter your username:");
                            info.setUsername(input.next());
                            System.out.println("Enter your e-mail(optional: enter 0 if you don't ant to):");
							String mail = input.next();
							if (!mail.equals("0"))
                            	info.setEmail(mail);
                            System.out.println("Enter password for account:");
                            info.setPassword(input.next());
                            System.out.println("Enter your phone number:");
                            info.setMobileNumber(input.next());
                            System.out.println("Enter your license ID:");
                            info.setLicenseId(input.next());
							System.out.println("Enter your national ID:");
                            info.setNationalId(input.next());
                            //register the account to the system
							try {
								if (DriverAuthenticator.getInstance().register(info)) {
									System.out.println("Account registeration request created successfully, please wait for an admin to varify it.");
									System.out.println("-----------------------------------");
								} else {
									System.out.println("Something went wrong!. Please try again.");
								}
							} catch (Exception e) {
								System.out.println(e.toString());
							}
                            break;
                        }
						//Register -> go back
						default:
							break;
                    }
                    break;
                }
                //Main Menu -> login to your account
                case 2:
                {
                    System.out.println("1- Passenger account");
                    System.out.println("2- Driver account");
					System.out.println("3- Admin account");
                    System.out.println("4- Go back to Main Menu");
                    int accountChoice = input.nextInt();
                    switch(accountChoice)
                    {
                        //Login -> Passenger account
                        case 1:
                        {
							try{
								System.out.println("Enter your username:");
								String username = input.next();
								System.out.println("Enter your password:");
								String password = input.next();
								Passenger passenger = (Passenger)PassengerAuthenticator.getInstance().login(username, password);
								System.out.println("logged in successfully.");
								System.out.println("------------------------------");
	
								int passengerMenuChoice = 0;
								while(passengerMenuChoice != 3)
								{
									//Passenger Menu
									System.out.println("->Passenger Menu<-");
									System.out.println("1- request a ride");
									System.out.println("2- check received offers");
									System.out.println("3- Logout to Main Menu");
									passengerMenuChoice = input.nextInt();
									switch(passengerMenuChoice)
									{
										//Passenger account -> request a ride
										case 1:
										{
											System.out.println("Enter source area:");
											String source = input.next();
											System.out.println("Enter destination area:");
											String destination = input.next();
											System.out.println("How many passengers?:");
											int noOfPassengers = input.nextInt();
											passenger.requestRide(source, destination, noOfPassengers);
											System.out.println("Ride requested successfully.");
											System.out.println("------------------------------");
											break;
										}
										//Passenger account -> check received offers
										case 2:
										{
											if (passenger.checkOffers()){
												System.out.println("Choose the offer that you're interested in (Enter 0 if you want to go back): ");
												int offerNumber = input.nextInt();
												if (offerNumber == 0)
													break;
	
												int offerMenuChoice = 1;
												while (offerMenuChoice == 1){
													//offer action Menu
													System.out.println("->Offer Actions<-");
													System.out.println("1- Check driver rating");
													System.out.println("2- accept offer");
													System.out.println("3- deny offer");
													System.out.println("4- Go back to Passenger Menu");
													offerMenuChoice = input.nextInt();
													switch(offerMenuChoice)
													{
														//offer action Menu -> Check driver rating
														case 1:
															System.out.println("Driver rating = " + passenger.checkDriverRating(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1).getItsDriver()));
															break;
														//offer action Menu -> accept offer
														case 2:
															IDriver driverOfOffer = SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1).getItsDriver();
															passenger.acceptOffer(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1));
															System.out.println("Offer accepted successfully.");
															System.out.println("------------------------------");
															System.out.println("1- Rate driver\t 2- continue");
															int rateDriverChoice = input.nextInt();
															if (rateDriverChoice == 1){
																while (true){
																	try {
																		System.out.println("Enter rating value between 1 and 5:");
																		int ratingValue = input.nextInt();
																		passenger.rateDriver(driverOfOffer, ratingValue);
																		break;
																	} catch (Exception e) {
																		System.out.println(e.toString());
																	}
																}	
															}
															break;
														//offer action Menu -> deny offer
														case 3:
															passenger.denyOffer(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1));
															System.out.println("Offer denied successfully.");
															System.out.println("------------------------------");
															break;
														//offer action Menu -> Go back
														default:
															break;
													}
												}
											}
											break;
										}
										//Passenger account -> exit
										case 3:
											break;
										//Passenger account -> *wrong input*
										default:
											System.out.println("Invalid choice!");
											break;
									}
								}
							} catch (Exception e){
								System.out.println(e.toString());
							}
							break;
                        }
                        //Login -> Driver account
                        case 2:
                        {
                            try{
								System.out.println("Enter your username:");
								String username = input.next();
								System.out.println("Enter your password:");
								String password = input.next();
								Driver driver = (Driver)DriverAuthenticator.getInstance().login(username, password);
								System.out.println("logged in successfully.");
								System.out.println("------------------------------");
	
								int driverMenuChoice = 0;
								while(driverMenuChoice != 5)
								{
									//Driver Menu
									System.out.println("->Driver Menu<-");
									System.out.println("1- Add favourite area");
									System.out.println("2- List rides in favourite areas");
									System.out.println("3- List passengers' ratings");
									System.out.println("4- List my offers");
									System.out.println("5- Logout to Main Menu");
									driverMenuChoice = input.nextInt();
									switch(driverMenuChoice)
									{
										//Driver account -> Add favourite area
										case 1:
										{
											System.out.println("Enter name of favourite area:");
											driver.addFavoriteArea(input.next());
											System.out.println("Area added successfully.");
											System.out.println("------------------------------");
											break;
										}
										//Driver account -> List rides in favourite areas
										case 2:
										{
											if (driver.listRidesInFavouriteAreas()){
												System.out.println("Choose the Ride that you're interested in (Enter 0 if you want to go back): ");
												int rideNumber = input.nextInt();
												if (rideNumber == 0)
													break;
												System.out.println(driver.getFavouriteAreaRides().get(rideNumber-1));
												System.out.println("1- Suggest price\t 2- continue");
												int rideActionChoice = input.nextInt();
												if (rideActionChoice == 1){
													System.out.println("Enter price:");
													double price = input.nextDouble();
													driver.suggestPrice(driver.getFavouriteAreaRides().get(rideNumber-1), price);
													System.out.println("Offer sent successfully.");
													System.out.println("------------------------------");
												}
											}
											break;
										}
										//Driver account -> List passengers' ratings
										case 3:
										{
											driver.listPassengersRatings();
											break;
										}
										//Driver account -> List my offers
										case 4:
										{
											driver.viewMyOffers();
											break;
										}
										//Driver account -> exit
										case 5:
											break;
										//Driver account -> *wrong input*
										default:
											System.out.println("Invalid choice!");
											break;
									}
								}
							} catch (Exception e){
								System.out.println(e.toString());
							}
                            break;
                        }
						//Login -> Admin account
						case 3:
						{
							try{
								System.out.println("Enter your username:");
								String username = input.next();
								System.out.println("Enter your password:");
								String password = input.next();
								Admin admin = (Admin)AdminAuthenticator.getInstance().login(username, password);
								System.out.println("logged in successfully.");
								System.out.println("------------------------------");
	
								int adminMenuChoice = 0;
								while(adminMenuChoice != 4)
								{
									//Admin Menu
									System.out.println("->Admin Menu<-");
									System.out.println("1- List pending registration requests");
									System.out.println("2- suspend a user");
									System.out.println("3- unsuspend a user");
									System.out.println("4- Logout to Main Menu");
									adminMenuChoice = input.nextInt();
									switch(adminMenuChoice)
									{
										//Admin account -> List pending registration requests
										case 1:
										{
											if (admin.listPendingRegistrations()){
												System.out.println("Choose the request that you're interested in (Enter 0 if you want to go back): ");
												int requestNumber = input.nextInt();
												if (requestNumber == 0)
													break;
												IRegistrationRequest request = SystemData.getInstance().getRegistrationRequest(requestNumber-1);
												System.out.println(request);
												System.out.println("1- varify request\t 2- deny request\t 3- continue");
												int requestActionChoice = input.nextInt();
												if (requestActionChoice == 1){
													admin.verifyDriverRegistration(request);
													System.out.println("account varified successfully.");
												} else if (requestActionChoice == 2){
													admin.denyDriverRegistration(request);
													System.out.println("account denied successfully.");
												}
											}
											break;
										}
										//Admin account -> suspend a user
										case 2:
										{
											System.out.println("1- suspend passenger\t 2- suspend driver");
											int suspendChoice = input.nextInt();
											if (suspendChoice == 1){
												System.out.println("Enter username of the passenger to suspend:");
												String suspendUsername = input.next();
												IPassenger passengerToSuspend = SystemData.getInstance().getPassenger(suspendUsername);
												if (passengerToSuspend != null){
													admin.suspendUser(passengerToSuspend);
													System.out.println("Account suspended successfully.");
                            						System.out.println("-----------------------------------");
												} else {
													System.out.println("ERROR: This Passenger was not found");
												}
											} else if (suspendChoice == 2){
												System.out.println("Enter username of the driver to suspend:");
												String suspendUsername = input.next();
												IDriver driverToSuspend = SystemData.getInstance().getDriver(suspendUsername);
												if (driverToSuspend != null) {
													admin.suspendUser(driverToSuspend);
													System.out.println("Account suspended successfully.");
                            						System.out.println("-----------------------------------");
												} else {
													System.out.println("ERROR: This Driver was not found");
												}
											}
											break;
										}
										//Admin account -> unsuspend a user
										case 3:
										{
											System.out.println("1- unsuspend passenger\t 2- unsuspend driver");
											int unsuspendChoice = input.nextInt();
											if (unsuspendChoice == 1){
												System.out.println("Enter username of the passenger to unsuspend:");
												String unsuspendUsername = input.next();
												IPassenger passengerToUnsuspend = SystemData.getInstance().getPassenger(unsuspendUsername);
												if (passengerToUnsuspend != null) {
													admin.unsuspendUser(passengerToUnsuspend);
													System.out.println("Account unsuspended successfully.");
                            						System.out.println("-----------------------------------");
												} else {
													System.out.println("ERROR: This Passenger was not found");
												}
											} else if (unsuspendChoice == 2){
												System.out.println("Enter username of the driver to unsuspend:");
												String unsuspendUsername = input.next();
												IDriver driverToUnsuspend = SystemData.getInstance().getDriver(unsuspendUsername);
												if (driverToUnsuspend != null) {
													admin.unsuspendUser(driverToUnsuspend);
													System.out.println("Account unsuspended successfully.");
                            						System.out.println("-----------------------------------");
												} else {
													System.out.println("ERROR: This Driver was not found");
												}
											}
											break;
										}
										//Admin account -> exit
										case 4:
											break;
										//Admin account -> *wrong input*
										default:
											System.out.println("Invalid choice!");
											break;
									}
								}
							} catch (Exception e){
								System.out.println(e.toString());
							}
							break;
						}
						//Login -> go back
						default:
							break;
                    }
                    break;
                }
                //Main Menu -> Exit
                case 3:
                    break;
                //Main Menu -> *wrong input*
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
		input.close();
	}

}
