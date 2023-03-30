/* eslint-disable prefer-template */
import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
// import { useNavigate } from 'react-router-dom';

import { PillBasket } from '../../assets/pilldata/index';

const SPillCard = styled.div`
  width: 80vw;
  height: 35vw;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 13px 27px -5px,
    rgba(0, 0, 0, 0.3) 0px 8px 16px -8px;
  display: flex;
  align-items: center;
  padding: 2vw 2vw 2vw 5vw;
  border-radius: 4vw;
`;

const SImg = styled.img`
  width: 50vw;
  height: 30vw;
  /* background-color: gray; */
  border-radius: 5vw;
`;

const SRightBox = styled.div`
  margin-left: 2vw;
  /* display: block; */
`;

const SBasketButton = styled.div`
  display: flex;
  align-items: center;
  width: 15vw;
  height: 7vw;
  border: 1px solid #00c192;
  border-radius: 3vw;
  margin: 1vw 0;
  justify-content: space-around;
`;

const SButtonImg = styled.img`
  display: flex;
  height: 5vw;
  width: 5vw;
`;

const SBox = styled.div`
  width: 40vw;
  height: 10vw;
  display: flex;
  justify-content: space-between;
  margin: 3vw 0;
  align-items: center;
`;

const SNameText = styled.div`
  font-size: 5vw;
  margin: 10vw 1vw;
`;

const SIngreText = styled.div`
  font-size: 3vw;
  margin-right: 2vw;
`;

const SButtonText = styled.div`
  font-size: 1vw;
`;

function DrugCard({ card }) {
  return (
    <div>
      <SPillCard>
        <SImg src={'https://' + card.drug_img} alt={card.drug_img} />
        <SRightBox>
          <SNameText>{card.drug_name}</SNameText>
          <SBox>
            {card.drug_ingre !== 'null' ? (
              <SIngreText>{card.drug_ingre}</SIngreText>
            ) : (
              <SIngreText>정보없음</SIngreText>
            )}
            {/* {card.drug_ingre.length < 7 && ? (
              <SIngreText>{card.drug_ingre}</SIngreText>
            ) : (
              <SIngreText>{card.drug_ingre.slice(0, 7) + '...'}</SIngreText>
            )} */}
            <SBasketButton>
              <SButtonImg src={PillBasket} alt={PillBasket} />
              <SButtonText>약바구니</SButtonText>
            </SBasketButton>
          </SBox>
        </SRightBox>
      </SPillCard>
    </div>
  );
}

DrugCard.propTypes = {
  card: PropTypes.shape({
    drug_id: PropTypes.number,
    drug_name: PropTypes.string,
    drug_ingre: PropTypes.string,
    drug_img: PropTypes.string,
  }),
};

DrugCard.defaultProps = {
  card: {
    drug_id: null,
    drug_img: null,
    drug_name: null,
    drug_ingre: null,
  },
};

export default DrugCard;
