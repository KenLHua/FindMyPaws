import './modal.css';

const Modal = ({ handleClose, show, children, name }) => {
    const showHideClassName = show ? "modal display-block" : "modal display-none";

    return (
        <div className={showHideClassName}>
            <section className="modal-main text-center p-3">
                <h3 className="text-center">{children}</h3>
                <hr />
                <button className="btn btn-light btn-block" type="button" onClick={handleClose}>
                    Close
                </button>
            </section>
        </div>
    );
};

export default Modal