export const AvatarImage = () => {
    return (
		<img className="user-info__avatar" src={process.env.PUBLIC_URL + '/img/avatar.svg'} alt="аватарка"/>
    )
}