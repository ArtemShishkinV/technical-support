import {BrowserRouter} from 'react-router-dom';
import React from 'react';
import {DefaultNavbar} from "./components/DefaultNavbar";
import {AppRouter} from "./components/AppRouter";
import {UserProvider} from "./hooks/UserProvider";

function App() {
    // const context = useContext(AppContext);
    //
    // const [isAuth, setIsAuth] = useState(false);
    // const [isLoading, setLoading] = useState(true);
    // const [user, setUser] = useState(context.user);
    //
    // const [fetchUser, _, error] = useFetching(async () => {
    //     const resp = await EmployeeService.getById(localStorage.getItem("id"), localStorage.getItem("token"))
    //     setUser(resp.data)
    //     console.log(resp)
    // })
    //
    // useEffect(() => {
    //     if (!user) fetchUser()
    //     if (localStorage.getItem('auth')) {
    //         console.log("test...")
    //         setIsAuth(true)
    //     }
    //     setLoading(false)
    // }, [])

    return (
        // <AppContext.Provider value={{
        //     user,
        //     setUser,
        //     isAuth,
        //     setIsAuth,
        //     isLoading
        // }}>
        <BrowserRouter>
            <UserProvider>
                <DefaultNavbar/>
                <AppRouter/>
            </UserProvider>
        </BrowserRouter>
        // </AppContext.Provider>
    )
}

export default App;
