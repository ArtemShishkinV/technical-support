export function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.token) {
        return {
            headers: {
                'Authorization': user.token
            }
        };
    }
}