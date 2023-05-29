import {useState} from "react";

export const useFetching = (callback) => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState('');

    const fetching = async (...args) => {
        try {
            setIsLoading(true)
            await callback(...args)
            console.log(isLoading)
        } catch (e) {
            setError(e.message);
        } finally {
            setIsLoading(false)
        }
        console.log(isLoading)
    }

    return [fetching, isLoading, error]
}