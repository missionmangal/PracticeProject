Reference -> https://androidwave.com/retrying-request-with-retrofit-android/
1. Define Retry annotation ->
		Create a @Retry annotation interface. Which APIs annotated with this annotation retry functionally will auto-enable.
		Here a have set the default attempts is 3 you can change it based on your app need.
		
2. Write a retry call adapter factory
		Create a new java class named RetryCallAdapterFactory which extends CallAdapter.Factory. 
		In this factory class, we’ll check is request is annotated with @Retry or not. 
		If annotated then every failure request try to call again at least 3 times.
		-> need to implement these three class
		1. static final class RetryCallAdapter<R, T> implements CallAdapter<R>
		2. static final class RetryingCall<R> implements Call<R>
		3. static final class RetryCallback<T> implements Callback<T>


3. Create POJO for server response

4. Write a UserApiService interface for Retrofit
		@Retry
	    @GET("user")
		Call<UserResponse> getUsers();

5. Create a Retrofit Client and add RetryCallAdapterFactory with client
	create a retrofit instance that returns UserApiService service class. 
	And add RetryCallAdapterFactory with retrofit client.
	->.addCallAdapterFactory(RetryCallAdapterFactory.create())
       