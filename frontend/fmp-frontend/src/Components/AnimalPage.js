import React, { Component } from 'react'

class AnimalPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: props.id,
            animalName: props.animalName,
            species: props.species,
            breed: props.breed,
            weight: props.weight,
            description: props.description,
            ownerName: props.ownerName,
            ownerPhone: props.ownerPhone,
            dateLost: props.dateLost,
            found: props.found,
            reward: props.reward,
        }

        this.foundJSX = ""
        if (this.state.found) {
            this.foundJSX = (
                <h4>Found</h4>
            )
        }
        else {
            this.foundJSX = (
                <h4>Lost</h4>
            )
        }
    }

    

    render() {
        return (
            <div className="container-fluid mt-5 border border-dark bg-light">
                <div className="row">
                    <div className="col-6">
                        <img className="img-fluid" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
                    </div>
                    <div className="col-6">
                        <h2>{this.state.animalName}<hr /></h2>
                        <h2>Found: {this.foundJSX}</h2>
                    </div>
                </div>
            </div>
        )
    }
}

export default AnimalPage