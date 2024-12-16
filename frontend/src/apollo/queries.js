import gql from "graphql-tag";

export const getAllApplications = (token) => ({
    query: gql`
      query	getApplications {
          applications {
              id
              owner {
                id,
                username
              }
              team {
                id,
                name
              }
              name
              description
              createdAt
              completed
              needRoles {
                  id
                  name
              }
          }
      }
      `,
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });

export const getApplications = (token, userId) => ({
  query: gql`
    query	getApplications ($filters: ApplicationmodelFilter){
        applications (filters: $filters) {
            id
            team {
              id,
              name
            }
            name
            description
            createdAt
            completed
            needRoles {
                id
                name
            }
        }
    }
    `,
    variables: {
        filters: {
            owner: {
                id: {
                    exact: userId
                }
          }
        }
    },
  fetchPolicy: 'no-cache',
  context: {
    headers: {
      Authorization: `JWT ${token}`
    }
  }
});

export const qRoles = (token, categoryId) => ({
    query: gql`
        query roles ($filter: RoleModelFilter) {
            roles (filters: $filter) {
                id
                name
            }
        }
      `,
    variables: {
      "filter": {
        "category": {
          "id": {
            "exact": categoryId
          }
        }
      }
    },
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });

  export const getMyTeams = (token, userId) => ({
    query: gql`
        query getMyTeams ($filters: TeamModelFilter!){
            teams(filters: $filters) {
                id
                name
                description
                createdAt
                completed
                members {
                  user {
                    id
                    username
                  }
                  role {
                    name
                  }
                }
                needRoles {
                  id
                  name
                }                
            }
        }
      `,
      variables: {
          filters: {
              owner: {
                  id: {
                      exact: userId
                  }
            }
          }
      },
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });

  export const getAllTeams = (token) => ({
    query: gql`
        query getAllTeams {
            teams {
                id
                owner {
                  username
                }
                name
                description
                createdAt
                completed
                members {
                  user {
                    id
                    username
                  }
                  role {
                    name
                  }
                }
                needRoles {
                  id
                  name
                }                
            }
        }
      `,
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });

  export const getAllTeamsName = (token) => ({
    query: gql`
        query getAllTeamsName {
            teams {
                id
                name              
            }
        }
      `,
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });  

  export const getMyRoles = (token) => ({
    query: gql`
        query me {
            me {
                roles {
                id
                name
                }
            }
        }
      `,
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });

  export const getMyTeamsShort = (token, userId) => ({
    query: gql`
        query getMyTeams($filters: TeamModelFilter) {
          teams(filters: $filters) {
            id
            name
            needRoles {
              id
              name
            }            
          }
        }
      `,
    variables: {
      "filters": {
        "owner": {
          "id": {
            "exact": userId
          }
        }
      }
    },
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });


  export const applicationsRequests = (token, userId) => ({
    query: gql`
      query applicationsRequests ($filters: ApplicationEnterTeamModelFilter) {
        applicationsRequests (filters: $filters) {
          id
          application {
            name
            team {
              id
            }            
          }
          user {
            username
          }
          role {
            id
            name
          }
          description
          createdAt
        }
      }
    `,
    variables: {
      "filters": {
        "application": {
          "owner": {
            "id": {
              "exact": userId
            }
          }
        }
      }
    },
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });


  export const qGetAllUsers = (token, userId) => ({
    query: gql`
      query users($filters: UserModelFilter!) {
        users (filters: $filters){
          id
          roles {
            id
            name
          }
          username
          members {
            id
            team {
              name
            }
            role {
              id
              name
            }
          }
        }
      }
      `,
    variables: {
      "filters": {
        "NOT": {
          "id": {
            "exact": userId
          }
        }
      }
    },
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });

  export const qGetRoleCategories = (token) => ({
    query: gql`
      query getRoleCategories {
        roleCategories {
          id
          name
        }
      }
    `,
    fetchPolicy: 'no-cache',
    context: {
      headers: {
        Authorization: `JWT ${token}`
      }
    }
  });