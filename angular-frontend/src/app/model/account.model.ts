export interface Account {
  id? : string,
  balance? : number,
  type? : string,
  createdAt? : string,
  customerId? : number
}

export enum RequestStatus {
  SUCCESS , ERROR
}
export interface AccountListState {
  accounts? : Account[],
  status? : RequestStatus,
  errorMessage? : string
}
