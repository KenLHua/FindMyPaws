import React, { Component } from 'react'

class AnimalPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
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
    }

    render() {
        return (
            <div className="container-fluid">
                <div className="row">
                    <div className="col-6">
                        <img className="img-fluid" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
                    </div>
                    <div className="col-6">
                        <h2></h2>
                    </div>
                </div>
            </div>
        )
    }
}